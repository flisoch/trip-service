package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
ФИО, адрес проживания, место работы, возраст, фото, дополнительная информация.
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String email;
    @Column(name = "hash_password")
    String hashedPassword;
    String name;
    String lastname;
    String middlename;
    String address;
    @Column(name = "working_place")
    String job;
    Integer age;
    String photo;   //path?
    @Column(name = "additional_info")
    String additionalInfo;

    @ManyToMany(mappedBy = "passangers")
    List<Trip> bookedTrips;

    @OneToMany(mappedBy = "commentator")
    List<TripComment> myComments;

    @OneToMany(mappedBy = "iniciator")
    List<Trip> trips;
}
