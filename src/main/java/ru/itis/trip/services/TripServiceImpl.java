package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trip.dao.TripCommentDao;
import ru.itis.trip.dao.TripDao;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.entities.forms.TripForm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    TripDao tripDao;
    TripCommentDao commentDao;

    @Autowired
    public TripServiceImpl(TripDao tripDao, TripCommentDao commentDao) {
        this.tripDao = tripDao;
        this.commentDao = commentDao;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripDao.getAllNotExpired();
    }

    @Override
    public TripDto getById(Long id) {
        Trip trip = tripDao.read(id).get();
        List<TripComment> comments = commentDao.getTripComments(trip);
        trip.setComments(comments);
        return TripDto.from(trip);
    }

    @Override
    public List<TripDto> getBookedByUser(User user) {
        return tripDao.getBookedTripByUser(user).stream().map(TripDto::from).collect(Collectors.toList());
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
        String dateTime = request.getParameter("date");
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        return tripDao.getByParameters(departure, destination, freeSeats, dateTime);
    }

    @Override
    public Trip createTrip(TripForm tripForm, User iniciator) {
        Trip trip = Trip.from(tripForm);
        trip.setIniciator(iniciator);
        tripDao.create(trip);
        return trip;
    }

    @Override
    public void updateTrip(TripForm tripForm, Long tripId, User iniciator) {
        Trip trip = Trip.from(tripForm);
        trip.setId(tripId);
        trip.setIniciator(iniciator);
        tripDao.update(trip);
    }

    @Override
    public void sendApply(Long tripId, Long userId) {
        tripDao.sendApply(tripId, userId);
    }

    @Override
    public HashMap<String, List<Request>> getRequsets(User user) {
        return tripDao.getRequests(user);
    }

    @Override
    public void deleteRequestById(Long id) {
        tripDao.deleteRequestById(id);
    }

    @Override
    public void deleteTripById(Long id) {
        tripDao.delete(id);
    }

    @Override
    public void acceptOrDenyRequest(Long requestId, boolean accepted) {
        if (accepted) {
            Request request = tripDao.findRequestById(requestId);
            tripDao.addUserToTrip(request.getUser().getId(), request.getTrip().getId());
        }
        tripDao.deleteRequestById(requestId);
    }
}
