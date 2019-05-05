package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.forms.UserCommentForm;
import ru.itis.trip.entities.util.LocalDateTimeConverter;

import javax.persistence.Convert;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserComment {
    Long id;
    User commentator;
    User commentatee;
    String text;
    @Convert(converter = LocalDateTimeConverter.class)
    LocalDateTime date;

    public static UserComment from(UserCommentForm userComment) {
        return UserComment.builder()
                .text(userComment.getText())
                .date(LocalDateTime.now())
                .build();
    }
}
