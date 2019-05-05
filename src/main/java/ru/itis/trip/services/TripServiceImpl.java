package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.trip.dao.user.UserDao;
import ru.itis.trip.dao.comments.TripCommentDao;
import ru.itis.trip.dao.request.RequestRepository;
import ru.itis.trip.dao.trip.TripRepository;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.TripCommentDto;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.entities.forms.TripForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TripServiceImpl implements TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    UserDao userDao;
    @Autowired
    TripCommentDao commentDao;

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.getAllNotExpired();
    }

    @Override
    public TripDto getById(Long id) {
        Trip trip = tripRepository.findById(id).get();
        List<TripComment> comments = commentDao.getTripComments(trip);
        trip.setComments(comments);
        return TripDto.from(trip);
    }

    @Override
    @Transactional
    public List<TripDto> getBookedByUser(User user) {
        List<Trip> trips = user.getBookedTrips();
        return tripRepository.getBookedByUserId(user.getId()).stream().map(trip -> {
            TripDto tripDto = TripDto.from(trip);
            List<TripComment> comments = trip.getComments();
            List<User> passangers = trip.getPassangers();
            tripDto.setComments(comments.stream().map(TripCommentDto::from).collect(Collectors.toList()));
            tripDto.setPassangers(passangers.stream()
                    .map(UserDto::from).collect(Collectors.toList()));
            return tripDto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Trip> getTripsByUser(User user) {
        List<Trip> trips = user.getTrips();
        return tripRepository.getByUserId(user.getId());
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
//        return tripRepository.getByParameters(departure, destination, freeSeats, date);
        return tripRepository.getAllNotExpired();
    }

    @Override
    public Trip createTrip(TripForm tripForm, User iniciator) {
        Trip trip = Trip.from(tripForm);
        trip.setIniciator(iniciator);
        tripRepository.save(trip);
        return trip;
    }

    @Override
    public void updateTrip(TripForm tripForm, Long tripId, User iniciator) {
        Trip trip = Trip.from(tripForm);
        trip.setId(tripId);
        trip.setIniciator(iniciator);
        tripRepository.save(trip);
    }

    @Override
    public void sendApply(Long tripId, Long userId) {
        Trip trip = tripRepository.findById(tripId).get();
        User user = userDao.read(userId).get();
        requestRepository.save(Request.builder().trip(trip).user(user).build());
    }

    @Override
    public List<Request> getRequsets(User user) {
        return requestRepository.findByUserId(user.getId());
    }

    @Override
    public void deleteRequestById(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public void acceptOrDenyRequest(Long requestId, boolean accepted) {
        if (accepted) {
            Request request = requestRepository.findById(requestId).get();
            tripRepository.addUserToTrip(request.getUser().getId(), request.getTrip().getId());
        }
        requestRepository.deleteById(requestId);
    }
}
