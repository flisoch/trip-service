package ru.itis.trip.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripForm {

    String departurePoint;
    String arrivalPoint;
    String date;
    String info;
    int seatsNumber;

}
