package ru.itis.trip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.forms.NewTripForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {
    Long id;
    User iniciator;
    String departurePoint;
    String arrivalPoint;
    String info;
    Long date;
    int freeSeats;
    boolean expired;

    List<User> passangers;
    List<TripComment> comments;

    public static Trip from(NewTripForm tripForm) {
        return   Trip.builder()
                .arrivalPoint(tripForm.getDestination())
                .departurePoint(tripForm.getDeparture())
                .freeSeats(tripForm.getSeats())
                .date(stringDateToLong(tripForm.getTime_to()))
                .info(tripForm.getBio())
                .build();
    }

    public static Long stringDateToLong(String dateTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        Date date;
        long epoch = 0L;
        try {
            date = dateFormat.parse(dateTime);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch;
    }
}
