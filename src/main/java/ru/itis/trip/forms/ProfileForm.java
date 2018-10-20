package ru.itis.trip.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileForm {
    String email;
    String password;

    String name;
    String surname;
    String middleName;
    String address;
    String job;
    Integer age;
    String photo;
    String additionalInfo;
}