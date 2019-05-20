package ru.itis.trip.services;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.dto.TripCommentDto;

import java.util.List;

public interface CommentService {
    List<TripComment> getComments(Trip trip);

    TripCommentDto saveComment(TripComment tripComment);

    void deleteComment(Long id);
}
