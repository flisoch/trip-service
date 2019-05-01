package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trip.dao.UserCommentDao;
import ru.itis.trip.entities.UserComment;
import ru.itis.trip.entities.dto.UserCommentDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCommentServiceImpl implements UserCommentService {
    UserCommentDao commentDao;

    @Autowired
    public UserCommentServiceImpl(UserCommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<UserCommentDto> getCommentsByUserId(Long userId) {
        return commentDao.getUserComments(userId).stream().map(UserCommentDto::from).collect(Collectors.toList());
    }

    @Override
    public void saveComment(UserComment userComment) {
        commentDao.create(userComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentDao.delete(id);
    }
}
