package ru.itis.trip.services;

import ru.itis.trip.entities.Trip;

import java.util.List;

public interface TripService {

    List<Trip> getAllTrips();

    Trip getById(Long id);
}
