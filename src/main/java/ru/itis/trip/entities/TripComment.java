package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripComment {
    Long id;
    User commentator;
    Trip trip;
    String text;
    Long date;

}
