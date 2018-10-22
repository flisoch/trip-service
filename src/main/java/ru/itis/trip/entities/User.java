package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
ФИО, адрес проживания, место работы, возраст, фото, дополнительная информация.
 */
public class User {
    Long id;
    String username;
    String email;
    String hashedPassword;
    String name;
    String lastname;
    String middlename;
    String address;
    String job;
    Integer age;
    String photo;   //path?
    String additionalInfo;
}
