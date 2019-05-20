package ru.itis.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.TripComment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripCommentDto {
    private Long id;
    private UserDto commentator;
    private String text;
    private long dateTime;

    public static TripCommentDto from(TripComment tripComment) {
        long epoch = tripComment.getDate().toInstant(ZoneOffset.UTC).toEpochMilli();
        return TripCommentDto.builder()
                .id(tripComment.getId())
                .commentator(UserDto.from(tripComment.getCommentator()))
                .text(tripComment.getText())
                .dateTime(epoch)
                .build();
    }
}
