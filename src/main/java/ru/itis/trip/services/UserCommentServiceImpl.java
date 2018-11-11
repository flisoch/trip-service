package ru.itis.trip.services;

import ru.itis.trip.dao.UserCommentDao;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;

import java.util.List;

public class UserCommentServiceImpl implements UserCommentService {
    UserCommentDao commentDao;

    public UserCommentServiceImpl(UserCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<UserComment> getCommentsByUser(User user) {
        return commentDao.getUserComments(user);
    }

    @Override
    public void saveComment(UserComment userComment) {
        commentDao.create(userComment);
    }
}
