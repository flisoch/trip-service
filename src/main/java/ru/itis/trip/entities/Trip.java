package ru.itis.trip.entities;

import lombok.*;
import ru.itis.trip.entities.forms.TripForm;
import ru.itis.trip.entities.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@ToString(exclude = {"iniciator", "passangers", "comments"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    User iniciator;
    @Column(name = "departure_point")
    String departurePoint;
    @Column(name = "arrival_point")
    String arrivalPoint;
    @Column(name = "info")
    String info;
    @Column(name = "datetime")
    @Convert(converter = LocalDateTimeConverter.class)
    LocalDateTime date;

    @Column(name = "free_seats")
    int freeSeats;
    @Transient
    boolean expired;

    @ManyToMany
    @JoinTable(name = "booked_trip",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> passangers;
    @OneToMany(mappedBy = "trip")
    List<TripComment> comments;
    @OneToMany(mappedBy = "trip")
    List<Request> tripRequests;

    public static Trip from(TripForm tripForm) {
        return Trip.builder()
                .arrivalPoint(tripForm.getDestination())
                .departurePoint(tripForm.getDeparture())
                .freeSeats(tripForm.getSeats())
                .info(tripForm.getInfo())
                .build();
    }

}
