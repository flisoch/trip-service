package ru.itis.trip.repositories.request;

import ru.itis.trip.entities.Request;

import java.util.List;

public interface RequestRepositoryCustom {
    List<Request> findByUserId(Long userId);
}
