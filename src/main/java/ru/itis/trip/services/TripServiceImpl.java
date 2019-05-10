package ru.itis.trip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.trip.entities.*;
import ru.itis.trip.entities.dto.RequestDto;
import ru.itis.trip.entities.dto.TripCommentDto;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.entities.forms.TripForm;
import ru.itis.trip.repositories.comments.TripCommentJdbcRepository;
import ru.itis.trip.repositories.request.RequestRepository;
import ru.itis.trip.repositories.trip.TripRepository;
import ru.itis.trip.repositories.user.UserDao;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public TripDto getById(Long id, User user) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no trip sith such id"));
        TripDto tripDto = TripDto.from(trip);
        tripDto.setStatus(getTripStatusForUser(trip, user));
        tripDto.setPassangers(trip.getPassangers().stream().map(UserDto::from).collect(Collectors.toList()));
        tripDto.setComments(trip.getComments().stream().map(TripCommentDto::from).collect(Collectors.toList()));
        return tripDto;
    }

    @Override
    @Transactional
    public List<TripDto> getBookedByUser(User user) {
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
    public List<TripDto> getTripsByUser(User user) {
        return tripRepository.getByUserId(user.getId()).stream().map(TripDto::from).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TripDto> getTripsWithParameters(User user, Map<String, String> searchParameters) {
        String departure = searchParameters.get("departure");
        String destination = searchParameters.get("destination");
        String seats = searchParameters.get("seats");
        String date = searchParameters.get("time_to");
        LocalDateTime dateTime;
        if (date == null || date.equals("")) {
            dateTime = LocalDateTime.now();
        } else {
            dateTime = LocalDateTime.parse(date);
        }
        List<Trip> trips = tripRepository.getByParameters(departure, destination, seats, dateTime);
        return trips.stream().map(trip -> {
            TripDto tripDto = TripDto.from(trip);
            tripDto.setStatus(getTripStatusForUser(trip, user));
            return tripDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Trip createTrip(TripForm tripForm, User iniciator) {
        Trip trip = Trip.from(tripForm);
        trip.setIniciator(iniciator);
        tripRepository.save(trip);
        return trip;
    }

    @Override
    @Transactional
    public void updateTrip(TripForm tripForm, Long tripId, User user) {
        String action = tripForm.getAction();
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("no trip with such id"));
        if (action != null) {
            switch (action) {
                case "cancelRequest":
                    Optional<Request> anyRequest = trip.getTripRequests().stream().
                            filter(request -> request.getUser().equals(user)).findAny();
                    anyRequest.ifPresent(requestRepository::delete);
                    break;
                case "leaveTrip":
                    trip.getPassangers().removeIf(u -> u.equals(user));
                    break;
                case "kickUser":
                    User userToKick = userDao.findById(tripForm.getUserId())
                            .orElseThrow(() -> new IllegalArgumentException("no user with such id"));
                    trip.getPassangers().removeIf(u -> u.equals(userToKick));
                    break;
                default:
                    throw new IllegalArgumentException("given action command is not found");
            }
        } else {
            trip = Trip.from(tripForm);
            trip.setIniciator(user);
        }
        tripRepository.save(trip);
    }

    @Override
    public void sendApply(Long tripId, Long userId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("no trip with such id"));
        User user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("no user with such id"));
        ;
        requestRepository.save(Request.builder().trip(trip).user(user).build());
    }

    @Override
    public List<RequestDto> getRequsets(User user) {
        return requestRepository.findByUserId(user.getId()).stream().map(RequestDto::from).collect(Collectors.toList());
    }

    @Override
    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public void acceptOrDenyRequest(Long requestId, boolean accepted) {
        if (accepted) {
            Request request = requestRepository.findById(requestId)
                    .orElseThrow(() -> new IllegalArgumentException("no request with such id"));
            tripRepository.addUserToTrip(request.getUser().getId(), request.getTrip().getId());
        }
        requestRepository.deleteById(requestId);
    }

    @Override
    public void deleteRequestById(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    @Transactional
    public TripStatus getTripStatusForUser(Trip trip, User user) {
        boolean wished = trip.getTripRequests().stream().anyMatch(request -> request.getUser().getId().equals(user.getId()));
        boolean booked = trip.getPassangers().contains(user);
        boolean my = trip.getIniciator().getId().equals(user.getId());
        if (booked) {
            return TripStatus.BOOKED;
        } else if (wished) {
            return TripStatus.WISHED;
        } else if (my) {
            return TripStatus.MY;
        } else {
            return TripStatus.NEUTRAL;
        }
    }
}
