package ru.itis.trip.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegistrationForm {
    String username;
    String email;
    String password;
    String confirmedPassword;
}
