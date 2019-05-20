package ru.itis.trip.repositories.comments;

import ru.itis.trip.repositories.CrudRepository;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;

import java.util.List;

public interface TripCommentJdbcRepository extends CrudRepository<TripComment> {
    List<TripComment> getTripComments(Trip trip);
}
