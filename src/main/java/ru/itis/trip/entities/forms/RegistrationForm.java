package ru.itis.trip.entities.forms;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String email;
    private String password;
    private String confirmedPassword;
}
