package ru.itis.trip.dao;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;

import java.util.List;

public interface TripCommentDao extends Dao<TripComment> {
    List<TripComment> getTripComments(Trip trip);
}
