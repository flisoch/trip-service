package ru.itis.trip.dao.implementation;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.trip.dao.implementation.mappers.RowMapper;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TripDao implements ru.itis.trip.dao.TripDao {

    private static final String CREATE_QUERY = "INSERT INTO trip(arrival_point, departure_point, dateTime, free_seats, initiator_id,info) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE trip SET arrival_point = ?, departure_point = ?, dateTime = ?, free_seats = ?, initiator_id = ?, info = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM trip WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * from trip where id = ?";
    private static final String SELECT_BY_ID_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where t.id = ?";
    private static final String SELECT_BY_INICIATOR_ID = "SELECT * from trip where initiator_id = ?";
    private static final String SELECT_BY_DIRECTION = "SELECT * from trip where departure_point = ? AND arrival_point = ?";
    private static final String SELECT_BY_DIRECTION_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where departure_point = ? AND arrival_point = ?";
    private static final String SELECT_BY_DIRECTION_DATE = "SELECT * from trip where departure_point = ? AND arrival_point = ? AND dateTime = ?";
    private static final String SELECT_BY_DIRECTION_DATE_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.idervice_user s on t.initiator_id = s.id " +
            "where departure_point = ? AND arrival_point = ? AND dateTime = ?";
    private static final String SELECT_BY_ID_WITH_EMPTY_USER = "SELECT t.id,arrival_point,departure_point,datetime,free_seats,initiator_id,info,username from trip t join service_user s on t.initiator_id = s.id WHERE t.id = ?";;
    private static final String SELECT_BY_USER_WITH_EMPTY_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where s.id = ?";
    private static final String SELECT_REQUEST_BY_USER_ID = "SELECT a.id, a.trip_id, a.user_id, u.username from trip_user_apply a " +
            "join trip t on a.trip_id = t.id " +
            "join service_user u on a.user_id = u.id " +
            "WHERE t.initiator_id = ?";
    private static final String DELETE_REQUEST_QUERY = "DELETE FROM trip_user_apply WHERE trip_id = ? AND user_id = ?";
    private static final String SELECT_BOOKED_BY_USER_ID = "SELECT * from book b inner join trip t on b.trip_id=t.id WHERE user_id = ?";


    Connection connection;
    JdbcTemplate jdbcTemplate;

    private RowMapper<Trip> tripMapperWithoutUser = new RowMapper<Trip>() {
        @Override
        @SneakyThrows
        public Trip rowMap(ResultSet resultSet) {
            return Trip.builder()
                    .id(resultSet.getLong("id"))
                    .arrivalPoint(resultSet.getString("arrival_point"))
                    .departurePoint(resultSet.getString("departure_point"))
                    .date(resultSet.getTimestamp("dateTime").getTime())
                    .iniciator(User.builder().id(resultSet.getLong("initiator_id")).build())
                    .info(resultSet.getString("info"))
                    .freeSeats(resultSet.getInt("free_seats"))
                    .expired(resultSet.getTimestamp("dateTime").getTime() < new Timestamp(System.currentTimeMillis()).getTime())
                    .build();
        }
    };

    private RowMapper<Request> requestMapper = new RowMapper<Request>() {
        @Override
        @SneakyThrows
        public Request rowMap(ResultSet resultSet) {
            User user = User.builder()
                    .id(resultSet.getLong("user_id"))
                    .username(resultSet.getString("username"))
                    .build();

            Trip trip = Trip.builder()
                    .id(resultSet.getLong("trip_id"))
                    .build();

            return Request.builder()
                    .id(resultSet.getLong("id"))
                    .trip(trip)
                    .user(user)
                    .build();
        }
    };

    private RowMapper<Trip> tripMapperWithEmptyUser = new RowMapper<Trip>() {
        @Override
        @SneakyThrows
        public Trip rowMap(ResultSet resultSet) {
            User user = User.builder()
                    .id(resultSet.getLong("initiator_id"))
                    .username(resultSet.getString("username"))
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

    private RowMapper<Trip> tripMapperWithUser = new RowMapper<Trip>() {
        @Override
        @SneakyThrows
        public Trip rowMap(ResultSet resultSet) {
            User user = User.builder()
                    .id(resultSet.getLong("id"))
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

    private org.springframework.jdbc.core.RowMapper<Trip> mapper = ((resultSet, i) -> {
        User user = User.builder()
                .id(resultSet.getLong("user_id"))
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
    });


    @SneakyThrows
    @Override
    public void deleteRequest(Long userId, Long tripId) {

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_QUERY);
        preparedStatement.setLong(1, tripId);
        preparedStatement.setLong(2, userId);
        preparedStatement.execute();

    }

    public TripDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SneakyThrows
    @Override
    public void addUserToTrip(Long userId, Long tripId) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO book(trip_id, user_id) VALUES (?,?)");
        statement.setLong(1,tripId);
        statement.setLong(2,userId);
        statement.execute();

        statement = connection.prepareStatement("UPDATE trip SET free_seats = free_seats - 1 WHERE id = ?");
        statement.setLong(1,tripId);
        statement.execute();
    }

    @SneakyThrows
    @Override
    public void sendApply(Long tripId, Long userId) {
        PreparedStatement statement = connection.prepareStatement("SELECT * from book WHERE trip_id = ? and user_id = ?");
        statement.setLong(1,tripId);
        statement.setLong(2,userId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return;
        }
        statement = connection.prepareStatement("INSERT INTO trip_user_apply(trip_id, user_id) VALUES (?,?)");
        statement.setLong(1,tripId);
        statement.setLong(2,userId);
        statement.execute();
    }

    @Override
    public List<Trip> getBookedTripByUser(User user) {
        return jdbcTemplate.query(SELECT_BOOKED_BY_USER_ID, mapper, user.getId());
    }

    @Override
    public HashMap<String, List<Trip>> getByUserId(Long userId) {
        HashMap<String,List<Trip>> trips = new HashMap<>();
        trips.put("active",new ArrayList<Trip>());
        trips.put("expired", new ArrayList<Trip>());
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_WITH_EMPTY_USER);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithEmptyUser.rowMap(resultSet);
                if(trip.isExpired()){
                    trips.get("expired").add(trip);
                }
                else {
                    trips.get("active").add(trip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public List<Request> getRequests(User user) {
        List<Request> requests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_REQUEST_BY_USER_ID);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Request request = requestMapper.rowMap(resultSet);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Trip> getByUser(User user) {
        List<Trip> trips = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_INICIATOR_ID);
            statement.setLong(1,user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithEmptyUser.rowMap(resultSet);
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
                if(trip.getFreeSeats() == 0){
                    continue;
                }
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
                if(trip.getFreeSeats() == 0){
                    continue;
                }
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
                if(resultSet.getTimestamp("dateTime").getTime() < new Timestamp(System.currentTimeMillis()).getTime()) {
                    continue;
                }
                if(resultSet.getLong("free_seats") == 0){
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
            resultSet.next();
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
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_WITH_EMPTY_USER);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Trip trip = tripMapperWithEmptyUser.rowMap(resultSet);
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
            preparedStatement.setLong(7,model.getId());
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
