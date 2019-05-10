package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.forms.UserCommentForm;
import ru.itis.trip.entities.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment_user")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "commentator_id")
    private User commentator;
    @ManyToOne
    @JoinColumn(name = "commentatee_id")
    private User commentatee;
    private String text;
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "datetime")
    private LocalDateTime date;

    public static UserComment from(UserCommentForm userComment) {
        return UserComment.builder()
                .text(userComment.getText())
                .date(LocalDateTime.now())
                .build();
    }
}
