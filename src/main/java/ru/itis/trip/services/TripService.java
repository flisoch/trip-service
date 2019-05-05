package ru.itis.trip.services;

import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.entities.forms.TripForm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface TripService {

    List<Trip> getAllTrips();

    TripDto getById(Long id);
    List<Trip> getTripsWithParameters(User user);

    Trip createTrip(TripForm tripForm, User iniciator);

    void updateTrip(TripForm tripForm, Long tripId, User user);

    void sendApply(Long tripId, Long id);

    List<Request> getRequsets(User user);

    List<Trip> getTripsByUser(User id);

    List<TripDto> getBookedByUser(User user);

    void deleteRequestById(Long id);

    void deleteTripById(Long id);

    void acceptOrDenyRequest(Long requestId, boolean accepted);
}
