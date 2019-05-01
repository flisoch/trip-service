package ru.itis.trip.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
    String username;
    String email;
    String password;
    String confirmedPassword;
}
