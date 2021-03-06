package ru.itis.trip.repositories.trip;

import ru.itis.trip.entities.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepositoryCustom {

    List<Trip> getAllNotExpired();

    List<Trip> getBookedByUserId(Long userId);

    void addUserToTrip(Long passangerId, Long tripId);

    List<Trip> getByUserId(Long userId);

    List<Trip> getByParameters(String departure, String destination, String freeSeats, LocalDateTime date);
}
