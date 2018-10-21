package ru.itis.trip.dao.implementation;

import lombok.SneakyThrows;
import ru.itis.trip.dao.implementation.mappers.RowMapper;
import ru.itis.trip.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao implements ru.itis.trip.dao.UserDao {

    private static final String SQL_CREATE_QUERY = "INSERT INTO service_user(email, hash_password, " +
            "first_name) VALUES (?,?,?)";
    private static final String SQL_UPDATE_QUERY = "UPDATE service_user SET VALUES (email = ?, hash_password = ?, " +
            "first_name = ?, surname = ?, working_place = ?, age = ?, additional_info = ?, photo = ?, address = ?)";
    private static final String SQL_DELETE_QUERY = "DELETE FROM service_user WHERE id = ?";
    private static final String SQL_SELECT_BY_ID_QUERY = "SELECT * from service_user where id = ?";
    private static final String SQL_SELECT_BY_NAME_QUERY = "SELECT * from service_user where name = ?";

    private Connection connection;

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .hashedPassword(resultSet.getString("hash_password"))
                    .name(resultSet.getString("first_name"))
                    .surname(resultSet.getString("last_name"))
                    .job(resultSet.getString("working_place"))
                    .photo(resultSet.getString("photo"))
                    .age(resultSet.getInt("age"))
                    .additionalInfo(resultSet.getString("additional_info"))
                    .address(resultSet.getString("address"))
                    .build();
        }
    };

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /*@Override
    public Optional<User> getByUsername(String username) {

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_NAME_QUERY);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            User user = userMapper.rowMap(resultSet);
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }*/

    @Override
    public void create(User model) {
        try {
            System.out.println(connection);
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,model.getEmail());
            preparedStatement.setString(2,model.getHashedPassword());
            preparedStatement.setString(3,model.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            model.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> read(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID_QUERY);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            User user = userMapper.rowMap(resultSet);
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(User model) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_QUERY);

            preparedStatement.setString(1,model.getEmail());
            preparedStatement.setString(2,model.getHashedPassword());
            preparedStatement.setString(3,model.getName());
            preparedStatement.setString(4,model.getSurname());
            preparedStatement.setString(5,model.getJob());
            preparedStatement.setInt(6,model.getAge());
            preparedStatement.setString(7,model.getAdditionalInfo());
            preparedStatement.setString(8,model.getPhoto());
            preparedStatement.setString(9,model.getAddress());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
