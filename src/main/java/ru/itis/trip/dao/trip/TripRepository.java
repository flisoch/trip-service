package ru.itis.trip.dao.trip;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.itis.trip.entities.Trip;


public interface TripRepository extends CrudRepository<Trip, Long>, TripRepositoryCustom  {
}
