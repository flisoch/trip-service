package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.forms.UserCommentForm;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserComment {
    Long id;
    User commentator;
    User commentatee;
    String text;
    Long date;

    public static UserComment from(UserCommentForm userComment) {
        return UserComment.builder()
                .text(userComment.getText())
                .date(System.currentTimeMillis())
                .build();
    }
}
