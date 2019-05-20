package ru.itis.trip.repositories;

import java.util.Optional;


public interface CrudRepository<T> {

    T create(T model);

    Optional<T> read(Long id);

    T update(T model);

    void delete(Long id);
}


