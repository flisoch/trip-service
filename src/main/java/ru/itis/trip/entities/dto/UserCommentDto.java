package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.UserComment;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCommentDto {
    private Long id;
    private UserDto commentator;
    private UserDto commentatee;
    private String text;
    private LocalDateTime dateTime;

    public static UserCommentDto from(UserComment userComment) {
        return UserCommentDto.builder()
                .id(userComment.getId())
                .commentator(UserDto.from(userComment.getCommentator()))
                .text(userComment.getText())
                .dateTime(userComment.getDate())
                .build();
    }
}