package ru.itis.trip.dao.implementation;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TripDao implements ru.itis.trip.dao.TripDao {

    private static final String CREATE_QUERY = "INSERT INTO trip(arrival_point, departure_point, dateTime, free_seats, initiator_id,info) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE trip SET arrival_point = ?, departure_point = ?, dateTime = ?, free_seats = ?, initiator_id = ?, info = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM trip WHERE id = ?";
    private static final String SELECT_BY_INICIATOR_ID = "SELECT * from trip where initiator_id = ?";
    private static final String SELECT_BY_DIRECTION_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where departure_point = ? AND arrival_point = ?";
    private static final String SELECT_BY_DIRECTION_DATE_WITH_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.idervice_user s on t.initiator_id = s.id " +
            "where departure_point = ? AND arrival_point = ? AND dateTime = ?";
    private static final String SELECT_BY_ID_WITH_EMPTY_USER = "SELECT t.id,arrival_point,departure_point,datetime,free_seats,initiator_id,info,username from trip t join service_user s on t.initiator_id = s.id WHERE t.id = ?";
    ;
    private static final String SELECT_BY_USER_WITH_EMPTY_USER = "SELECT * from trip t join service_user s on t.initiator_id = s.id where s.id = ?";
    private static final String SELECT_REQUEST_BY_USER_ID = "SELECT a.id, a.trip_id, a.user_id, u.username from trip_user_apply a " +
            "join trip t on a.trip_id = t.id " +
            "join service_user u on a.user_id = u.id " +
            "WHERE t.initiator_id = ? or u.id = ?";
    private static final String DELETE_REQUEST_QUERY = "DELETE FROM trip_user_apply WHERE trip_id = ? AND user_id = ?";
    private static final String SELECT_BOOKED_BY_USER_ID = "SELECT u.photo as user_photo, u.username, b.trip_id, b.user_id," +
            "arrival_point, departure_point, t.datetime, t.info,t.free_seats from booked_trip b inner join trip t on b.trip_id=t.id join service_user u " +
            "on t.initiator_id=u.id WHERE user_id = ?";
    private static final String DELETE_REQUEST_BY_ID = "DELETE FROM trip_user_apply WHERE id = ?";
    private static final String INSERT_TO_BOOKED = "INSERT INTO booked_trip(trip_id, user_id) VALUES (?,?)";
    private static final String DECRESE_SEATS = "UPDATE trip SET free_seats = free_seats - 1 WHERE id = ?";

    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    private RowMapper<Trip> tripMapperWithoutUser = new RowMapper<Trip>() {
        @Override
        @SneakyThrows
        public Trip mapRow(ResultSet resultSet, int i) {
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
        public Request mapRow(ResultSet resultSet, int i) {
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
        public Trip mapRow(ResultSet resultSet, int i) {
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
        public Trip mapRow(ResultSet resultSet, int i) {
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

    private RowMapper<Trip> tripUserWithPhotoMapper = ((resultSet, i) -> {
        User user = User.builder()
                .id(resultSet.getLong("user_id"))
                .username(resultSet.getString("username"))
                .photo(resultSet.getString("user_photo"))
                .build();

        return Trip.builder()
                .id(resultSet.getLong("trip_id"))
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
    public void deleteRequestById(Long id) {
        jdbcTemplate.update(DELETE_REQUEST_BY_ID, id);
    }

    @SneakyThrows
    @Override
    public void deleteRequest(Long userId, Long tripId) {
        jdbcTemplate.update(DELETE_REQUEST_QUERY, tripId, userId);
    }

    public TripDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SneakyThrows
    @Override
    public void addUserToTrip(Long userId, Long tripId) {
        jdbcTemplate.update(INSERT_TO_BOOKED, tripId, userId);
        //Todo: create a trigger:
        jdbcTemplate.update(DECRESE_SEATS);
    }

    @SneakyThrows
    @Override
    public void sendApply(Long tripId, Long userId) {
        jdbcTemplate.update("SELECT * from booked_trip WHERE trip_id = ? and user_id = ?",
                tripId, userId);
        //todo:Todo: create a trigger:
        jdbcTemplate.update("INSERT INTO trip_user_apply(trip_id, user_id) VALUES (?,?)",
                tripId, userId);

    }

    @Override
    public List<Trip> getBookedTripByUser(User user) {
        return jdbcTemplate.query(SELECT_BOOKED_BY_USER_ID, tripUserWithPhotoMapper, user.getId());
    }

    @Override
    public HashMap<String, List<Trip>> getByUserId(Long userId) {
        HashMap<String, List<Trip>> trips = new HashMap<>();
        trips.put("active", new ArrayList<Trip>());
        trips.put("expired", new ArrayList<Trip>());
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_USER_WITH_EMPTY_USER);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithEmptyUser.mapRow(resultSet, 0);
                if (trip.isExpired()) {
                    trips.get("expired").add(trip);
                } else {
                    trips.get("active").add(trip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public HashMap<String, List<Request>> getRequests(User user) {
        HashMap<String, List<Request>> requests = new HashMap<>();
        requests.put("from_me", new ArrayList<>());
        requests.put("to_me", new ArrayList<>());
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_REQUEST_BY_USER_ID);
            statement.setLong(1, user.getId());
            statement.setLong(2, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Request request = requestMapper.mapRow(resultSet, 0);
                if (request.getUser().getId().equals(user.getId())) {
                    requests.get("from_me").add(request);
                } else {
                    requests.get("to_me").add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Trip> getByUser(User user) {
        List<Trip> trips = new ArrayList<>();
        return jdbcTemplate.query(SELECT_BY_INICIATOR_ID, tripMapperWithEmptyUser, user.getId());
    }

    @Override
    public List<Trip> getByParameters(String departure, String destination, String freeSeats, String dateTime) {
        List<Trip> trips;
        StringBuilder query = new StringBuilder("SELECT s.id as user_id, s.photo as user_photo, s.username, t.id as trip_id, arrival_point, departure_point, ");
        query.append("dateTime, info, free_seats from trip t join service_user s on t.initiator_id = s.id ");

        query.append("WHERE ");
        boolean hasParameters = false;
        if (departure != null && !departure.equals("")) {
            query.append("departure_point = \'");
            query.append(departure);
            query.append("\' AND ");
            hasParameters = true;
        }
        if (destination != null && !destination.equals("")) {
            query.append("arrival_point = \'");
            query.append(destination);
            query.append("\' AND ");
            hasParameters = true;
        }
        if (freeSeats != null && !freeSeats.equals("")) {
            query.append("free_seats = ");
            query.append(Long.parseLong(freeSeats));
            query.append(" AND ");
            hasParameters = true;
        }
        if (dateTime != null && !dateTime.equals("")) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
            Date date;
            Long epoch = 0L;
            try {
                date = dateFormat.parse(dateTime);
                epoch = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            query.append("dateTime > \'");
            query.append(new Timestamp(epoch));
            query.append("\' AND ");
            hasParameters = true;
        }

        query.append("dateTime > now() AND free_seats > 0");
        trips = jdbcTemplate.query(query.toString(), tripUserWithPhotoMapper);
        return trips;
    }

    @Override
    public List<Trip> getByDirection(String departurePoint, String arrivalPoint) {
        List<Trip> trips = new ArrayList<>();
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_DIRECTION_WITH_USER);
            statement.setString(1, departurePoint);
            statement.setString(2, arrivalPoint);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithUser.mapRow(resultSet, 0);
                if (trip.getFreeSeats() == 0) {
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
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_DIRECTION_DATE_WITH_USER);
            statement.setString(1, departurePoint);
            statement.setString(2, arrivalPoint);
            statement.setTimestamp(3, new Timestamp(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = tripMapperWithUser.mapRow(resultSet, 0);
                if (trip.getFreeSeats() == 0) {
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
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM trip");
            while (resultSet.next()) {
                if (resultSet.getTimestamp("dateTime").getTime() < new Timestamp(System.currentTimeMillis()).getTime()) {
                    continue;
                }
                if (resultSet.getLong("free_seats") == 0) {
                    continue;
                }
                Trip trip = tripMapperWithoutUser.mapRow(resultSet, 0);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public Optional<Trip> create(Trip model) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(CREATE_QUERY, new String[]{"id"});
                    preparedStatement.setString(1, model.getArrivalPoint());
                    preparedStatement.setString(2, model.getDeparturePoint());
                    preparedStatement.setTimestamp(3, new Timestamp(model.getDate()));
                    preparedStatement.setInt(4, model.getFreeSeats());
                    preparedStatement.setLong(5, model.getIniciator().getId());
                    preparedStatement.setString(6, model.getInfo());
                    return preparedStatement;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        return Optional.of(model);

    }

    @Override
    public Optional<Trip> read(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID_WITH_EMPTY_USER, tripMapperWithEmptyUser, id));
    }

    @Override
    public void update(Trip model) {
        jdbcTemplate.update(UPDATE_QUERY,
                model.getArrivalPoint(),
                model.getDeparturePoint(),
                new Timestamp(model.getDate()),
                model.getFreeSeats(),
                model.getIniciator().getId(),
                model.getInfo(),
                model.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}
