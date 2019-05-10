package ru.itis.trip.dao.comments;

import ru.itis.trip.dao.CrudDao;
import ru.itis.trip.entities.UserComment;

import java.util.List;

public interface UserCommentDao extends CrudDao<UserComment> {
    List<UserComment> getUserComments(Long userId);
}
