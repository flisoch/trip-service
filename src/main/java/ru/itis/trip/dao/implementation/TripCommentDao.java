package ru.itis.trip.dao.implementation;

import ru.itis.trip.dao.implementation.mappers.RowMapper;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripCommentDao implements ru.itis.trip.dao.TripCommentDao {


    private static final String SELECT_BY_ID_WITH_EMPTY_MODELS = "SELECT * FROM  comment_trip WHERE id = ?";
    private static final String SELECT_BY_ID_WITH_TRIP = "SELECT * FROM  comment_trip c JOIN trip t ON c.trip_id = t.id WHERE c.id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comment_trip WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE  comment_trip SET VALUES (trip_id = ?,commentator_id = ?,text = ?,dateTime = ?) WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO comment_trip VALUES (trip_id,commentator_id,text,dateTime) VALUES (?,?,?,?)";
    private static final String SELECT_COMMENTS_BY_TRIP_ID = "SELECT  * FROM  comment_trip WHERE  trip_id = ?";


    Connection connection;

    RowMapper<TripComment> tripCommentMapper = resultSet -> {
        try {

            TripComment tripComment = TripComment.builder()
                    .id(resultSet.getLong("c.id"))
                    .trip(Trip.builder().id(resultSet.getLong("trip_id")).build())
                    .commentator(User.builder().id(resultSet.getLong("commentator_id")).build())
                    .text(resultSet.getString("text"))
                    .date(resultSet.getTimestamp("dateTime").getTime())
                    .build();
            return tripComment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    public TripCommentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<TripComment> getTripComments(Trip trip)  {
        List<TripComment> tripComments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_COMMENTS_BY_TRIP_ID);
            statement.setLong(1,trip.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                TripComment comment = tripCommentMapper.rowMap(resultSet);
                tripComments.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripComments;
    }

    @Override
    public boolean create(TripComment model) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1,model.getTrip().getId());
            preparedStatement.setLong(2,model.getCommentator().getId());
            preparedStatement.setString(3,model.getText());
            preparedStatement.setTimestamp(4,new Timestamp(model.getDate()));
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            model.setId(resultSet.getLong("id"));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<TripComment> read(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_WITH_EMPTY_MODELS);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            TripComment tripComment = tripCommentMapper.rowMap(resultSet);
            return Optional.of(tripComment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(TripComment model) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setLong(1,model.getTrip().getId());
            preparedStatement.setLong(2,model.getCommentator().getId());
            preparedStatement.setString(3,model.getText());
            preparedStatement.setTimestamp(4,new Timestamp(model.getDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
