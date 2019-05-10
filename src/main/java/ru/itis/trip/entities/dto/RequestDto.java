package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.Request;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    Long id;
    UserDto user;
    TripDto trip;

    public static RequestDto from(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .user(UserDto.from(request.getUser()))
                .trip(TripDto.from(request.getTrip()))
                .build();
    }
}
