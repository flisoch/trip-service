package ru.itis.trip.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserCommentForm {
    String text;
    Long commentatorId;
    Long commentateeId;
}
