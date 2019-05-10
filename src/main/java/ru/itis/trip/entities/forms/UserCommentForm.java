package ru.itis.trip.entities.forms;

import lombok.Data;

@Data
public class UserCommentForm {
    private String text;
    private Long commentatorId;
    private Long commentateeId;
}
