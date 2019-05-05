package ru.itis.trip.services;

import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.UserCommentDto;
import ru.itis.trip.entities.forms.UserCommentForm;

import java.util.List;

public interface UserCommentService {
    List<UserCommentDto> getCommentsByUserId(Long currentUserId);

    UserCommentDto saveComment(UserCommentForm userComment, User user);

    void deleteComment(Long id);
}
