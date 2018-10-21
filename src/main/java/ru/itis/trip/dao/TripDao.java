package ru.itis.trip.dao;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;

import java.util.List;

public interface TripDao extends Dao<Trip>{

    List<Trip> getByUser(User user);
    List<Trip> getByDirection(String departurePoint, String arrivalPoint);
    List<Trip> getByDirectionAndDate(String departurePoint, String arrivalPoint, Long date);
}
