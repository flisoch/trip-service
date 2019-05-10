package ru.itis.trip.dao.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.trip.entities.User;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findByRememberMeToken(String token);
    Optional<User> findByUsername(String username);
}
