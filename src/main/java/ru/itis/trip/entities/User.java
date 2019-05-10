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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @Column(name = "hash_password", length = 500)
    private String hashedPassword;
    private String name;
    private String lastname;
    private String middlename;
    private String address;
    @Column(name = "working_place")
    private String job;
    private Integer age;
    private String photo;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "token", length = 500)
    private String rememberMeToken;

    @ManyToMany(mappedBy = "passangers")
    private List<Trip> bookedTrips;

    @OneToMany(mappedBy = "commentator")
    private List<TripComment> myComments;
    @OneToMany(mappedBy = "commentator")
    private List<UserComment> myUserComments;

    @OneToMany(mappedBy = "iniciator")
    private List<Trip> trips;

    @OneToMany(mappedBy = "user")
    private List<Request> requests;

}
