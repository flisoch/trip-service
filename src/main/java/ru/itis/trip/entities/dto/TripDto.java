package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    Long date;
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
                .passangers(trip.getPassangers()!=null?trip.getPassangers().stream()
                        .map(UserDto::from).collect(Collectors.toList()) : new ArrayList<>())
                .comments(trip.getComments()!=null?trip.getComments().stream()
                        .map(TripCommentDto::from).collect(Collectors.toList()):null)
                .build();
    }
}
