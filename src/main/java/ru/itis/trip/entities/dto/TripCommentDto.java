package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripCommentDto {
    Long id;
    User commentator;
    String text;
    Long date;

    public static TripCommentDto from(TripComment tripComment) {
        return TripCommentDto.builder()
                .id(tripComment.getId())
                .commentator(tripComment.getCommentator())
                .text(tripComment.getText())
                .date(tripComment.getDate())
                .build();
    }
}
