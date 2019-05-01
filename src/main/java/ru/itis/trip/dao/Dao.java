package ru.itis.trip.dao;

import ru.itis.trip.entities.User;

import java.util.Optional;


public interface Dao<T> {

    Optional<T> create(T model);
    Optional<T> read(Long id);
    T update(T model);
    void delete(Long id);
}


