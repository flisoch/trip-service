package ru.itis.trip.forms;

import lombok.Data;


@Data
public class ProfileForm {
    private String email;
    private String password;
    private String username;
    private String name;
    private String surname;
    private String middlename;
    private String address;
    private String job;
    private Integer age;
    private String photo;
    private String additionalInfo;
}
