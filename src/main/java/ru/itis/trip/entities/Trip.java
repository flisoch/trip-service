package ru.itis.trip.entities;

import lombok.*;
import ru.itis.trip.entities.forms.TripForm;
import ru.itis.trip.entities.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@ToString(exclude = {"iniciator", "passangers", "comments", "tripRequests"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User iniciator;
    @Column(name = "departure_point")
    private String departurePoint;
    @Column(name = "arrival_point")
    private String arrivalPoint;
    @Column(name = "info")
    private String info;
    @Column(name = "datetime")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;

    @Column(name = "free_seats")
    private int freeSeats;
    @Transient
    private boolean expired;
    @Transient
    private TripStatus status;

    @ManyToMany
    @JoinTable(name = "booked_trip",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> passangers;
    @OneToMany(mappedBy = "trip")
    private List<TripComment> comments;
    @OneToMany(mappedBy = "trip")
    private List<Request> tripRequests;

    public static Trip from(TripForm tripForm) {
        return Trip.builder()
                .arrivalPoint(tripForm.getDestination())
                .departurePoint(tripForm.getDeparture())
                .freeSeats(tripForm.getSeats())
                .info(tripForm.getInfo())
                .date(LocalDateTime.parse(tripForm.getDate()))
                .build();
    }

}
