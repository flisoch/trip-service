package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {
    Long id;
    User iniciator;
    String departurePoint;
    String arrivalPoint;
    String info;
    Long date;
    int freeSeats;
    boolean expired;

    List<User> passangers;
}
