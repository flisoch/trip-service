package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.Trip;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    Long id;
    UserDto iniciator;
    String departurePoint;
    String arrivalPoint;
    String info;
    LocalDateTime date;
    int freeSeats;
    boolean expired;

    List<UserDto> passangers;
    List<TripCommentDto> comments;

    public static TripDto from(Trip trip) {
        return TripDto.builder()
                .id(trip.getId())
                .iniciator(UserDto.from(trip.getIniciator()))
                .departurePoint(trip.getDeparturePoint())
                .arrivalPoint(trip.getArrivalPoint())
                .info(trip.getInfo())
                .date(trip.getDate())
                .freeSeats(trip.getFreeSeats())
                .expired(trip.isExpired())
                .build();
    }
}
