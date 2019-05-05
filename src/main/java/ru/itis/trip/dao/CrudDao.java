package ru.itis.trip.dao;

import java.util.Optional;


public interface CrudDao<T> {

    T create(T model);

    Optional<T> read(Long id);

    T update(T model);

    void delete(Long id);
}


