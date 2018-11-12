package ru.itis.trip.services;

import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;

import java.util.List;

public interface UserCommentService {
    List<UserComment> getCommentsByUser(User currentUser);
    void saveComment(UserComment userComment);

    void deleteComment(Long id);
}
