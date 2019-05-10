package ru.itis.trip.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "trip_user_apply")
@Data
@ToString(exclude = {"user", "trip"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    Trip trip;
}
