package ru.itis.trip.dao.request;

import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.User;

import java.util.List;

public interface RequestRepositoryCustom {
    List<Request> findByUserId(Long userId);
}
