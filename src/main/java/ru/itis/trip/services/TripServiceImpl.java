package ru.itis.trip.services;

import ru.itis.trip.dao.TripDao;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.TripForm;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripServiceImpl implements TripService {
    TripDao tripDao;

    public TripServiceImpl(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripDao.getAllNotExpired();
    }

    @Override
    public Trip getById(Long id) {
        return tripDao.read(id).get();
    }

    @Override
    public List<Trip> getBookedByUser(User user) {
        return tripDao.getBookedTripByUser(user);
    }

    @Override
    public HashMap<String, List<Trip>> getTripsByUser(User user) {
        return tripDao.getByUserId(user.getId());
    }

    @Override
    public List<Trip> getTripsWithParameters(HttpServletRequest request) {
        /*String userId = request.getParameter("user_id");
        if(userId != null){
            return tripDao.getByUserId(Long.parseLong(userId));
        }*/
        String freeSeats = request.getParameter("seats");
        String dateTime = request.getParameter("time_to");
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        return tripDao.getByParameters(departure, destination,freeSeats,dateTime);
    }

    @Override
    public void createTrip(Trip trip) {
        tripDao.create(trip);
    }

    @Override
    public void updateTrip(HttpServletRequest request, TripForm tripForm) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        Date date;
        Long epoch = 0L;
        try {
            date = dateFormat.parse(tripForm.getDate());
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Trip trip = Trip.builder()
                .iniciator((User)request.getSession().getAttribute("current_user"))
                .info(tripForm.getInfo())
                .date(epoch)
                .freeSeats(tripForm.getSeatsNumber())
                .departurePoint(tripForm.getDeparturePoint())
                .arrivalPoint(tripForm.getArrivalPoint())
                .id(getId(request))
                .build();
        tripDao.update(trip);
    }

    @Override
    public void sendApply(Long tripId, Long userId) {
        tripDao.sendApply(tripId, userId);
    }

    @Override
    public void rejectRequest(Long userId, Long tripId) {
        tripDao.deleteRequest(userId, tripId);
    }

    @Override
    public void acceptRequest(Long userId, Long tripId) {
        tripDao.addUserToTrip(userId, tripId);
        tripDao.deleteRequest(userId,tripId);
    }

    @Override
    public HashMap<String, List<Request>> getRequsets(User user) {
        return tripDao.getRequests(user);
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/trips/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        Long id = null;
        if(matcher.find()){
            id = Long.valueOf(matcher.group(1));
        }

        return id;
    }
}
