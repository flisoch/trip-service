package ru.itis.trip.entities;

import lombok.*;
import ru.itis.trip.entities.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment_trip")
@Data
@ToString(exclude = {"commentator", "trip"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "commentator_id")
    private User commentator;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
    private String text;
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "datetime")
    private LocalDateTime date;

}
