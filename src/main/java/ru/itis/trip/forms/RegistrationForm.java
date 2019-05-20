package ru.itis.trip.forms;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String email;
    private String password;
    private String confirmedPassword;
}
