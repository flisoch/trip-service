package ru.itis.trip.repositories.comments;

import ru.itis.trip.repositories.CrudRepository;
import ru.itis.trip.entities.UserComment;

import java.util.List;

public interface UserCommentJdbcRepository extends CrudRepository<UserComment> {
    List<UserComment> getUserComments(Long userId);
}
