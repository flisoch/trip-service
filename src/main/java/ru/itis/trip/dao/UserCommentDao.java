package ru.itis.trip.dao;

import ru.itis.trip.entities.UserComment;

import java.util.List;

public interface UserCommentDao extends CrudDao<UserComment> {
    List<UserComment> getUserComments(Long userId);
}
