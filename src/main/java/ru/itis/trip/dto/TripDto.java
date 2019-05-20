package ru.itis.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripStatus;

import java.time.ZoneOffset;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private Long id;
    private UserDto iniciator;
    private String departurePoint;
    private String arrivalPoint;
    private String info;
    private long date;
    private int freeSeats;
    private boolean expired;
    private TripStatus status;

    private List<UserDto> passangers;
    private List<TripCommentDto> comments;

    public static TripDto from(Trip trip) {
        long epoch = trip.getDate().toInstant(ZoneOffset.UTC).toEpochMilli();
        return TripDto.builder()
                .id(trip.getId())
                .iniciator(UserDto.from(trip.getIniciator()))
                .departurePoint(trip.getDeparturePoint())
                .arrivalPoint(trip.getArrivalPoint())
                .info(trip.getInfo())
                .date(epoch)
                .freeSeats(trip.getFreeSeats())
                .expired(trip.isExpired())
                .build();
    }
}
