package ru.itis.trip.dao.implementation;

import lombok.SneakyThrows;
import ru.itis.trip.dao.implementation.mappers.RowMapper;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripDao implements ru.itis.trip.dao.TripDao {

    private static final String CREATE_QUERY = "INSERT INTO trip(arrival_point, departure_point, dateTime, free_seats, initiator_id,info) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE trip SET VALUES (arrival_point = ?, departure_point = ?, dateTime = ?, free_seats = ?, initiator_id = ?, info = ?)";
    private static final String DELETE_QUERY = "DELETE FROM trip WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * from trip where id = ?";
    private static final String SELECT_BY_ID_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where t.id = ?";
    private static final String SELECT_BY_INICIATOR_ID = "SELECT * from trip where initiator_id = ?";
    private static final String SELECT_BY_DIRECTION = "SELECT * from trip where departure_point = ? AND arrival_point = ?";
    private static final String SELECT_BY_DIRECTION_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where departure_point = ? AND arrival_point = ?";
    private static final String SELECT_BY_DIRECTION_DATE = "SELECT * from trip where departure_point = ? AND arrival_point = ? AND dateTime = ?";
    private static final String SELECT_BY_DIRECTION_DATE_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.idervice_user s on t.initiator_id = s.id " +
            "where departure_point = ? AND arrival_point = ? AND dateTime = ?";


    Connection connection;

    private RowMapper<Trip> tripMapperWithoutUser = new RowMapper<Trip>() {
        @Override
        @SneakyThrows
        public Trip rowMap(ResultSet resultSet) {
            return Trip.builder()
                    .id(resultSet.getLong("id"))
                    .arrivalPoint(resultSet.getString("arrival_point"))
                    .departurePoint(resultSet.getString("departure_point"))
                    .date(resultSet.getTimestamp("dateTime").getTime())
                    .info(resultSet.getString("info"))
                    .freeSeats(resultSet.getInt("free_seats"))
                    .expired(resultSet.getTimestamp("dateTime").getTime() < new Timestamp(System.currentTimeMillis()).getTime())
                    .build();
        }
    };

    private RowMapper<Trip> tripMapperWithUser = new RowMapper<Trip>() {
        @Override
        @SneakyThrows
        public Trip rowMap(ResultSet resultSet) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .hashedPassword(resultSet.getString("hash_password"))
                    .name(resultSet.getString("first_name"))
                    .lastname(resultSet.getString("last_name"))
                    .job(resultSet.getString("working_place"))
                    .photo(resultSet.getString("photo"))
                    .age(resultSet.getInt("age"))
                    .additionalInfo(resultSet.getString("additional_info"))
                    .address(resultSet.getString("address"))
                    .build();

            return Trip.builder()
                    .id(resultSet.getLong("id"))
                    .arrivalPoint(resultSet.getString("arrival_point"))
                    .departurePoint(resultSet.getString("departure_point"))
                    .date(resultSet.getTimestamp("dateTime").getTime())
                    .info(resultSet.getString("info"))
                    .freeSeats(resultSet.getInt("free_seats"))
                    .expired(resultSet.getTimestamp("dateTime").getTime() < new Timestamp(System.currentTimeMillis()).getTime())
                    .iniciator(user)
                    .build();
        }
    };

    public TripDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Trip> getByUser(User user) {
        List<Trip> trips = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_INICIATOR_ID);
            statement.setLong(1,user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithoutUser.rowMap(resultSet);
                trip.setIniciator(user);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public List<Trip> getByDirection(String departurePoint, String arrivalPoint) {
        List<Trip> trips = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_DIRECTION_WITH_USER);
            statement.setString(1, departurePoint);
            statement.setString(2, arrivalPoint);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithUser.rowMap(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public List<Trip> getByDirectionAndDate(String departurePoint, String arrivalPoint, Long date) {
        List<Trip> trips = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_DIRECTION_DATE_WITH_USER);
            statement.setString(1, departurePoint);
            statement.setString(2, arrivalPoint);
            statement.setTimestamp(3,new Timestamp(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithUser.rowMap(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public List<Trip> getAllNotExpired() {
        List<Trip> trips = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM trip");
            while (resultSet.next()) {
                if(resultSet.getTimestamp("dateTime").getTime() < new Timestamp(System.currentTimeMillis()).getTime()){
                    continue;
                }
                Trip trip = tripMapperWithoutUser.rowMap(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public boolean create(Trip model) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,model.getArrivalPoint());
            preparedStatement.setString(2,model.getDeparturePoint());
            preparedStatement.setTimestamp(3,new Timestamp(model.getDate()));
            preparedStatement.setInt(4,model.getFreeSeats());
            preparedStatement.setLong(5,model.getIniciator().getId());
            preparedStatement.setString(6,model.getInfo());
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
    public Optional<Trip> read(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Trip trip = tripMapperWithoutUser.rowMap(resultSet);
            return Optional.of(trip);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Trip model) {
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1,model.getArrivalPoint());
            preparedStatement.setString(2,model.getDeparturePoint());
            preparedStatement.setTimestamp(3,new Timestamp(model.getDate()));
            preparedStatement.setInt(4,model.getFreeSeats());
            preparedStatement.setLong(5,model.getIniciator().getId());
            preparedStatement.setString(6,model.getInfo());
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
