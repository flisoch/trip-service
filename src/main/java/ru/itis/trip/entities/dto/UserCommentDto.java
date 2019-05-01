package ru.itis.trip.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.UserComment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCommentDto {
    Long id;
    UserDto commentator;
    UserDto commentatee;
    String text;
    Long date;

    public static UserCommentDto from(UserComment userComment) {
        return UserCommentDto.builder()
                .id(userComment.getId())
                .commentator(UserDto.from(userComment.getCommentator()))
                .text(userComment.getText())
                .date(userComment.getDate())
                .build();
    }
}