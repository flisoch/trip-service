package ru.itis.trip.dao.implementation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TripCommentDaoImpl implements ru.itis.trip.dao.TripCommentDao {


    private static final String SELECT_BY_ID_WITH_EMPTY_MODELS = "SELECT * FROM  comment_trip WHERE id = ?";

    //language=SQL
    private static final String SELECT_BY_ID_WITH_USER = "SELECT c.id as comment_id,trip_id,commentator_id,text,datetime,username,photo as user_photo FROM  comment_trip AS c JOIN service_user AS u ON commentator_id = u.id WHERE trip_id = ?";

    private static final String SELECT_BY_ID_WITH_TRIP = "SELECT * FROM  comment_trip c JOIN trip t ON c.trip_id = t.id WHERE c.id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comment_trip WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE  comment_trip SET trip_id = ?,commentator_id = ?,text = ?,dateTime = ? WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO comment_trip (trip_id,commentator_id,text,dateTime) VALUES (?,?,?,?)";
    private static final String SELECT_COMMENTS_BY_TRIP_ID = "SELECT  * FROM  comment_trip WHERE  trip_id = ?";

    JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    RowMapper<TripComment> tripCommentMapper = (resultSet, i) -> {
        try {

            TripComment tripComment = TripComment.builder()
                    .id(resultSet.getLong("id"))
                    .trip(Trip.builder().id(resultSet.getLong("trip_id")).build())
                    .commentator(User.builder().id(resultSet.getLong("commentator_id")).build())
                    .text(resultSet.getString("text"))
                    .date(resultSet.getTimestamp("date").toLocalDateTime())
                    .build();
            return tripComment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };
    RowMapper<TripComment> tripWithUserCommentMapper = (resultSet, i) -> {
        try {

            User user = User.builder()
                    .id(resultSet.getLong("commentator_id"))
                    .photo(resultSet.getString("user_photo"))
                    .username(resultSet.getString("username"))
                    .build();
            TripComment tripComment = TripComment.builder()
                    .id(resultSet.getLong("comment_id"))
                    .trip(Trip.builder().id(resultSet.getLong("trip_id")).build())
                    .commentator(user)
                    .text(resultSet.getString("text"))
                    .date(resultSet.getTimestamp("date").toLocalDateTime())
                    .build();

            return tripComment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    public TripCommentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TripComment> getTripComments(Trip trip) {
        return jdbcTemplate.query(SELECT_BY_ID_WITH_USER, tripWithUserCommentMapper, trip.getId());
    }

    @Override
    public TripComment create(TripComment model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(CREATE_QUERY, new String[]{"id"});
                    preparedStatement.setLong(1, model.getTrip().getId());
                    preparedStatement.setLong(2, model.getCommentator().getId());
                    preparedStatement.setString(3, model.getText());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(model.getDate()));
                    return preparedStatement;
                }, keyHolder);

        model.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return model;
    }

    @Override
    public Optional<TripComment> read(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID_WITH_EMPTY_MODELS, tripCommentMapper, id));
    }

    @Override
    public TripComment update(TripComment model) {
        jdbcTemplate.update(UPDATE_QUERY,
                model.getTrip().getId(),
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
