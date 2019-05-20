package ru.itis.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.UserComment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCommentDto {
    private Long id;
    private UserDto commentator;
    private UserDto commentatee;
    private String text;
    private long dateTime;

    public static UserCommentDto from(UserComment userComment) {
        long epoch = userComment.getDate().toInstant(ZoneOffset.UTC).toEpochMilli();
        return UserCommentDto.builder()
                .id(userComment.getId())
                .commentator(UserDto.from(userComment.getCommentator()))
                .text(userComment.getText())
                .dateTime(epoch)
                .build();
    }
}