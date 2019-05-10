package ru.itis.trip.entities.forms;

import lombok.Data;


@Data
public class LoginForm {
    private String username;
    private String email;
    private String password;
}
