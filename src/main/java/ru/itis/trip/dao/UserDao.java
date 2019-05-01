package ru.itis.trip.dao;

import ru.itis.trip.entities.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
//    Optional<User> getByEmail(String email);

    Optional<User> getByUsername(String username);

    boolean addToken(User user, String token);

    Optional<User> getByToken(String token);
}
