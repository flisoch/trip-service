package ru.itis.trip.dao.implementation;

import ru.itis.trip.dao.implementation.mappers.RowMapper;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCommentDao implements ru.itis.trip.dao.UserCommentDao {

    private static final String CREATE_QUERY = "INSERT INTO comment_user VALUES (commentatee_id,commentator_id,text,dateTime) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE  comment_user SET VALUES (commentatee_id = ?,commentator_id = ?,text = ?,dateTime = ?) WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM comment_user WHERE id = ?";
    private static final String SELECT_BY_ID_WITH_EMPTY_MODELS = "SELECT * FROM  comment_user WHERE id = ?";
    private static final String SELECT_COMMENTS_BY_COMMENTEE_ID = "SELECT * FROM comment_user WHERE commentatee_id = ?";


    RowMapper<UserComment> userCommentMapper = resultSet -> {
        try {

            UserComment userComment= UserComment.builder()
                    .id(resultSet.getLong("c.id"))
                    .commentatee(User.builder().id(resultSet.getLong("commentatee_id")).build())
                    .commentator(User.builder().id(resultSet.getLong("commentator_id")).build())
                    .text(resultSet.getString("text"))
                    .date(resultSet.getTimestamp("dateTime").getTime())
                    .build();
            return userComment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    };

    Connection connection;

    public UserCommentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserComment> getUserComments(User user) {
        List<UserComment> userComment = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_COMMENTS_BY_COMMENTEE_ID);
            statement.setLong(1,user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                UserComment comment = userCommentMapper.rowMap(resultSet);
                userComment.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userComment;
    }

    @Override
    public void create(UserComment model) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1,model.getCommentatee().getId());
            preparedStatement.setLong(2,model.getCommentator().getId());
            preparedStatement.setString(3,model.getText());
            preparedStatement.setTimestamp(4,new Timestamp(model.getDate()));
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            model.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<UserComment> read(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_WITH_EMPTY_MODELS);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            UserComment tripComment = userCommentMapper.rowMap(resultSet);
            return Optional.of(tripComment);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void update(UserComment model) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setLong(1,model.getCommentatee().getId());
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