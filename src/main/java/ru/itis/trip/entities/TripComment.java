package ru.itis.trip.entities;

import lombok.*;
import ru.itis.trip.entities.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment_trip")
@Data
@ToString(exclude = {"commentator","trip"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "commentator_id")
    User commentator;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    Trip trip;
    String text;
    @Convert(converter = LocalDateTimeConverter.class)
    LocalDateTime date;

}
