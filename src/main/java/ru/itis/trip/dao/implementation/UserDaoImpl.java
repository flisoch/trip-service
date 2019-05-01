package ru.itis.trip.dao.implementation;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.trip.entities.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UserDaoImpl implements ru.itis.trip.dao.UserDao {

    private static final String SQL_CREATE_QUERY = "INSERT INTO service_user(email, hash_password, username) VALUES (?,?,?)";
    private static final String SQL_UPDATE_QUERY = "UPDATE service_user SET email = ?, hash_password = ?," +
            " name = ?, lastname = ?, working_place = ?, age = ?, additional_info = ?, photo = ?, address = ?, username = ? WHERE id = ?";
    private static final String SQL_DELETE_QUERY = "DELETE FROM service_user WHERE id = ?";
    private static final String SQL_SELECT_BY_ID_QUERY = "SELECT * from service_user where id = ?";
    private static final String SQL_SELECT_BY_USERNAME_QUERY = "SELECT * from service_user where username = ?";
    private static final String SQL_SELECT_BY_EMAIL_QUERY = "SELECT * from service_user where email = ?";
    private static final String INSERT_TOKEN = "UPDATE service_user SET token = ? WHERE id = ?";
    private static final String SQL_SELECT_BY_TOKEN_QUERY = "SELECT * from service_user where token = ?";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @SneakyThrows
        public User mapRow(ResultSet resultSet, int i) {
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .email(resultSet.getString("email"))
                    .hashedPassword(resultSet.getString("hash_password"))
                    .name(resultSet.getString("name"))
                    .lastname(resultSet.getString("lastname"))
                    .job(resultSet.getString("working_place"))
                    .photo(resultSet.getString("photo"))
                    .age(resultSet.getInt("age"))
                    .additionalInfo(resultSet.getString("additional_info"))
                    .address(resultSet.getString("address"))
                    .build();
        }
    };

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> create(User model) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_CREATE_QUERY, new String[]{"id"});
                    preparedStatement.setString(1, model.getEmail());
                    preparedStatement.setString(2, model.getHashedPassword());
                    preparedStatement.setString(3, model.getUsername());
                    return preparedStatement;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

        return Optional.of(model);
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID_QUERY, userMapper, id));
    }

    @Override
    public User update(User model) {
        jdbcTemplate.update(SQL_UPDATE_QUERY, model.getEmail(),
                model.getHashedPassword(),
                model.getName(),
                model.getLastname(),
                model.getJob(),
                model.getAge(),
                model.getAdditionalInfo(),
                model.getPhoto(),
                model.getAddress(),
                model.getUsername(),
                model.getId()
        );
        return model;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_QUERY, id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME_QUERY, userMapper, username));
    }


    @Override
    public Optional<User> getByToken(String token) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_TOKEN_QUERY, userMapper, token));
    }


    @Override
    public boolean addToken(User user, String token) {
        return jdbcTemplate.update(INSERT_TOKEN, token, user.getId()) != 0;
    }
}
