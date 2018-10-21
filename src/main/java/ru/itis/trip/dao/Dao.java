package ru.itis.trip.dao;

import java.util.Optional;


public interface Dao<T> {

    void create(T model);
    Optional<T> read(Long id);
    void update(T model);
    void delete(Long id);
}


