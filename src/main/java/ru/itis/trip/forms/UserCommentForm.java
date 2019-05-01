package ru.itis.trip.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentForm {
    String text;
    Long commentatorId;
    Long commentateeId;
}
