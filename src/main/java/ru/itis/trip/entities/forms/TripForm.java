package ru.itis.trip.entities.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class TripForm {
    String destination;
    String departure;
    String info;
    Integer seats;
    @NotEmpty(message = "new trip must have LocalDateTime date")
    String date;
    String action;
}
