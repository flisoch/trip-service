package ru.itis.trip.dao;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;

import java.util.List;

public interface TripCommentDao extends CrudDao<TripComment> {
    List<TripComment> getTripComments(Trip trip);
}
