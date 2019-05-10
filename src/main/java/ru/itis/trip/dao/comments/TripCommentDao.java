package ru.itis.trip.dao.comments;

import ru.itis.trip.dao.CrudDao;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;

import java.util.List;

public interface TripCommentDao extends CrudDao<TripComment> {
    List<TripComment> getTripComments(Trip trip);
}
