package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserComment {
    Long id;
    User commentator;
    User commentatee;
    String text;
    Long date;
}
