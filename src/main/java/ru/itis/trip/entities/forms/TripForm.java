package ru.itis.trip.entities.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TripForm {
    private String destination;
    private String departure;
    private String info;
    private Integer seats;
    @NotEmpty(message = "new trip must have LocalDateTime dateTime")
    private String date;
    private String action;
    private Long userId;
}
