package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {
    Long id;
    User iniciator;
    String departurePoint;
    String arrivalPoint;
    Long date;
    String time;
    int freeSeats;
    boolean expired;
}
