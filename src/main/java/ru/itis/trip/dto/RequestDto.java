package ru.itis.trip.dto;

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
    private Long id;
    private UserDto user;
    private TripDto trip;

    public static RequestDto from(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .user(UserDto.from(request.getUser()))
                .trip(TripDto.from(request.getTrip()))
                .build();
    }
}
