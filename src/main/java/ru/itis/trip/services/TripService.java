package ru.itis.trip.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.RequestDto;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.entities.forms.TripForm;

import java.util.List;
import java.util.Map;

public interface TripService {

    List<TripDto> getAllTrips(User user);

    TripDto getById(Long id, User user);

    List<TripDto> getTripsWithParameters(User user, Map<String, String> searchParameters);

    Trip createTrip(TripForm tripForm, User iniciator);

    void updateTrip(TripForm tripForm, Long tripId, User user);

    void sendApply(Long tripId, Long id);

    List<RequestDto> getRequsets(User user);

    List<TripDto> getTripsByUser(User id);

    List<TripDto> getBookedByUser(User user);

    void deleteRequestById(Long id);

    void deleteTripById(Long id);

    void acceptOrDenyRequest(Long requestId, boolean accepted);
}
