package ru.itis.trip.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"bookedTrips", "myComments", "requests", "myUserComments", "trips"})
@EqualsAndHashCode(of = {"id", "hashedPassword", "username"})
/*
ФИО, адрес проживания, место работы, возраст, фото, дополнительная информация.
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String email;
    @Column(name = "hash_password", length = 500)
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
    @Column(name = "token", length = 500)
    String rememberMeToken;

    @ManyToMany(mappedBy = "passangers")
    List<Trip> bookedTrips;

    @OneToMany(mappedBy = "commentator")
    List<TripComment> myComments;
    @OneToMany(mappedBy = "commentator")
    List<UserComment> myUserComments;

    @OneToMany(mappedBy = "iniciator")
    List<Trip> trips;

    @OneToMany(mappedBy = "user")
    List<Request> requests;

}
