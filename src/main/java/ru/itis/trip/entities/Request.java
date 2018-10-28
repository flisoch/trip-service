package ru.itis.trip.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    Long id;
    User user;
    Trip trip;
}
