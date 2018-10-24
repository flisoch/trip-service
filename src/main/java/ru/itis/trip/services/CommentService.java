package ru.itis.trip.services;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;

import java.util.List;

public interface CommentService {
    List<TripComment> getComments(Trip trip);

    void addComment(TripComment tripComment);
}
