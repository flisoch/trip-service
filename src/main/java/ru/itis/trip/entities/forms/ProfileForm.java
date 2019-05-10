package ru.itis.trip.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ProfileForm {
    String email;
    String password;
    String username;

    String name;
    String surname;
    String middlename;
    String address;
    String job;
    Integer age;
    String photo;
    String additionalInfo;
}
