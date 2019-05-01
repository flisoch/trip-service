package ru.itis.trip.services;

import ru.itis.trip.entities.UserComment;
import ru.itis.trip.entities.dto.UserCommentDto;

import java.util.List;

public interface UserCommentService {
    List<UserCommentDto> getCommentsByUserId(Long currentUserId);
    void saveComment(UserComment userComment);

    void deleteComment(Long id);
}
