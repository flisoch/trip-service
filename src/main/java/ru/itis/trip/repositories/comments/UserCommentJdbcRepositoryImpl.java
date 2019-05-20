package ru.itis.trip.repositories.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserCommentJdbcRepositoryImpl implements UserCommentJdbcRepository {

    private static final String CREATE_QUERY = "INSERT INTO comment_user (commentatee_id,commentator_id,text,dateTime) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE  comment_user SET VALUES (commentatee_id = ?,commentator_id = ?,text = ?,dateTime = ?) WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comment_user WHERE id = ?";
    private static final String SELECT_BY_ID_WITH_EMPTY_MODELS = "SELECT * FROM  comment_user WHERE id = ?";
    private static final String SELECT_COMMENTS_BY_COMMENTEE_ID = "SELECT c.id as id, c.commentator_id, c.commentatee_id, " +
            "c.text, c.datetime, u.username as commentator_username, u.photo as commentator_photo " +
            "FROM comment_user c INNER JOIN service_user u on c.commentator_id = u.id WHERE commentatee_id = ?";


    private RowMapper<UserComment> userCommentMapper = (resultSet, i) -> {
        try {
            User commentator = User.builder()
                    .id(resultSet.getLong("commentator_id"))
                    .username(resultSet.getString("commentator_username"))
                    .photo(resultSet.getString("commentator_photo"))
                    .build();
            return UserComment.builder()
                    .id(resultSet.getLong("id"))
                    .commentator(commentator)
                    .text(resultSet.getString("text"))
                    .date(resultSet.getTimestamp("datetime").toLocalDateTime())
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCommentJdbcRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<UserComment> getUserComments(Long userId) {
        return jdbcTemplate.query(SELECT_COMMENTS_BY_COMMENTEE_ID, userCommentMapper, userId);
    }

    @Override
    public UserComment create(UserComment model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(CREATE_QUERY, new String[]{"id"});
                    preparedStatement.setLong(1, model.getCommentatee().getId());
                    preparedStatement.setLong(2, model.getCommentator().getId());
                    preparedStatement.setString(3, model.getText());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(model.getDate()));
                    return preparedStatement;
                }, keyHolder);

        model.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return model;
    }

    @Override
    public Optional<UserComment> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID_WITH_EMPTY_MODELS, userCommentMapper, id));
    }

    @Override
    public UserComment update(UserComment model) {
        jdbcTemplate.update(UPDATE_QUERY,
                model.getCommentatee().getId(),
                model.getCommentator().getId(),
                model.getText(),
                Timestamp.valueOf(model.getDate())
        );
        return model;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
