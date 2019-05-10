package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.TripComment;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripCommentDto {
    private Long id;
    private UserDto commentator;
    private String text;
    private LocalDateTime dateTime;

    public static TripCommentDto from(TripComment tripComment) {
        return TripCommentDto.builder()
                .id(tripComment.getId())
                .commentator(UserDto.from(tripComment.getCommentator()))
                .text(tripComment.getText())
                .dateTime(tripComment.getDate())
                .build();
    }
}
