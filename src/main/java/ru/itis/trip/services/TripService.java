package ru.itis.trip.services;

import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.TripForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TripService {

    List<Trip> getAllTrips();

    Trip getById(Long id);
    List<Trip> getTripsWithParameters(HttpServletRequest request);

    void createTrip(Trip trip);

    void updateTrip(HttpServletRequest request, TripForm tripForm);

    void sendApply(Long tripId, Long id);

    List<Request> getRequsets(User user);

    void rejectRequest(Long userId, Long tripId);

    void acceptRequest(Long userId, Long tripId);

    List<Trip> getTripsByUser(User id);
}
