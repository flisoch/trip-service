package ru.itis.trip.services;

import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.forms.NewTripForm;
import ru.itis.trip.forms.TripForm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface TripService {

    List<Trip> getAllTrips();

    TripDto getById(Long id);
    List<Trip> getTripsWithParameters(HttpServletRequest request);

    Trip createTrip(NewTripForm tripForm, User iniciator);

    void updateTrip(HttpServletRequest request, TripForm tripForm);

    void sendApply(Long tripId, Long id);

    HashMap<String, List<Request>> getRequsets(User user);

    void rejectRequest(Long userId, Long tripId);

    void acceptRequest(Long userId, Long tripId);

    HashMap<String, List<Trip>> getTripsByUser(User id);

    List<Trip> getBookedByUser(User user);

    void deleteRequestById(Long id);

    void deleteTripById(Long id);
}
