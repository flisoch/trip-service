package ru.itis.trip.services;

import ru.itis.trip.dao.TripCommentDao;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    TripCommentDao tripCommentDao;

    public CommentServiceImpl(TripCommentDao tripCommentDao) {
        this.tripCommentDao = tripCommentDao;
    }

    @Override
    public void deleteComment(Long id) {
        tripCommentDao.delete(id);
    }

    @Override
    public List<TripComment> getComments(Trip trip) {
        return tripCommentDao.getTripComments(trip);
    }

    @Override
    public void saveComment(TripComment tripComment) {
        tripCommentDao.create(tripComment);
    }
}
