package ru.itis.trip.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class LoginForm {
    private String username;
    private String email;
    private String password;
}
