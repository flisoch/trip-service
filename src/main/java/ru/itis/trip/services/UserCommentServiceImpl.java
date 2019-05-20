package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;
import ru.itis.trip.dto.UserCommentDto;
import ru.itis.trip.forms.UserCommentForm;
import ru.itis.trip.repositories.comments.UserCommentJdbcRepository;
import ru.itis.trip.repositories.user.UserDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCommentServiceImpl implements UserCommentService {
    @Autowired
    private UserDao userDao;

    private UserCommentJdbcRepository commentDao;

    @Autowired
    public UserCommentServiceImpl(UserCommentJdbcRepository commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<UserCommentDto> getCommentsByUserId(Long userId) {
        return commentDao.getUserComments(userId).stream().map(UserCommentDto::from).collect(Collectors.toList());
    }

    @Override
    public UserCommentDto saveComment(UserCommentForm userCommentForm, User commentator) {
        User commentatee = userDao.findById(userCommentForm.getCommentateeId())
                .orElseThrow(() -> new IllegalArgumentException("no user with such id"));
        UserComment comment = UserComment.from(userCommentForm);
        comment.setCommentatee(commentatee);
        comment.setCommentator(commentator);
        UserComment savedComment = commentDao.create(comment);
        return UserCommentDto.from(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentDao.delete(id);
    }
}
