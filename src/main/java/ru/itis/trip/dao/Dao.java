package ru.itis.trip.dao;

import ru.itis.trip.entities.TripComment;

import java.util.Optional;


public interface Dao<T> {

    Optional<T> create(T model);
    Optional<T> read(Long id);
    void update(T model);
    void delete(Long id);
}


