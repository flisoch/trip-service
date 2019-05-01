package ru.itis.trip.dao;

import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;
import ru.itis.trip.forms.UserCommentForm;

import java.util.List;

public interface UserCommentDao extends Dao<UserComment>{
    List<UserComment> getUserComments(Long userId);
}
