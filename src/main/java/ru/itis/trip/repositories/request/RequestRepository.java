package ru.itis.trip.repositories.request;

import org.springframework.data.repository.CrudRepository;
import ru.itis.trip.entities.Request;


public interface RequestRepository extends CrudRepository<Request, Long>, RequestRepositoryCustom {

}
