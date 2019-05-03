package ru.itis.trip.dao;

import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;

import java.util.HashMap;
import java.util.List;

public interface TripDao extends Dao<Trip>{

    List<Trip> getByUser(User user);
    List<Trip> getByDirection(String departurePoint, String arrivalPoint);
    List<Trip> getByDirectionAndDate(String departurePoint, String arrivalPoint, Long date);

    List<Trip> getAllNotExpired();

    void sendApply(Long tripId, Long userId);

    HashMap<String, List<Trip>> getByUserId(Long userId);

    HashMap<String, List<Request>> getRequests(User user);

    void addUserToTrip(Long userId, Long tripId);

    List<Trip> getBookedTripByUser(User user);

    List<Trip> getByParameters(String departure, String destination, String freeSeats, String dateTime);

    void deleteRequestById(Long id);

    Request findRequestById(Long id);
}
