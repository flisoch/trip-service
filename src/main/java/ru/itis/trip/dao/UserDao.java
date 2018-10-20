package ru.itis.trip.dao;

import ru.itis.trip.entities.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> getByUsername(String username);
}
