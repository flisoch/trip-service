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
    String date;
    String time;
    int seatsNumber;
    boolean hasEmptySeats;
    boolean expired;

}
