package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trip.dao.comments.TripCommentDao;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.dto.TripCommentDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    TripCommentDao tripCommentDao;

    @Autowired
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
    public TripCommentDto saveComment(TripComment tripComment) {
        tripComment.setDate(LocalDateTime.now());
        return TripCommentDto.from(tripCommentDao.create(tripComment));
    }
}
