package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trip.dao.comments.UserCommentDao;
import ru.itis.trip.dao.user.UserDao;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;
import ru.itis.trip.entities.dto.UserCommentDto;
import ru.itis.trip.entities.forms.UserCommentForm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    UserDao userDao;

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
    public UserCommentDto saveComment(UserCommentForm userComment, User commentator) {
        Optional<User> commentatee = userDao.read(userComment.getCommentateeId());
        UserComment comment = UserComment.from(userComment);
        comment.setCommentatee(commentatee.get());
        comment.setCommentator(commentator);
        UserComment savedComment = commentDao.create(comment);
        return UserCommentDto.from(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentDao.delete(id);
    }
}
