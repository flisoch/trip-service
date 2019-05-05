package ru.itis.trip.dao.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.itis.trip.entities.Request;


public interface RequestRepository extends CrudRepository<Request, Long>, RequestRepositoryCustom {

}
