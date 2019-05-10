package ru.itis.trip.repositories.trip;

import org.springframework.data.repository.CrudRepository;
import ru.itis.trip.entities.Trip;


public interface TripRepository extends CrudRepository<Trip, Long>, TripRepositoryCustom {
}
