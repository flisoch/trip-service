package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.entities.forms.TripForm;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TripsController {
    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    CommentService commentService;

    @GetMapping("/trips/new")
    public String newTripPage(ModelMap modelMap, HttpServletRequest request) {
        return "newTrip";
    }

    @PostMapping("/trips")
    public String createTrip(TripForm form, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        Trip trip = tripService.createTrip(form, user);
        return "redirect:/trips/" + trip.getId() + "/edit";
    }

    @GetMapping("/trips/{tripId}")
    public String tripPage(@PathVariable Long tripId,
                           ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        modelMap.put("trip", tripService.getById(tripId, user));

        return "tripById";
    }

    @GetMapping("/trips/{tripId}/edit")
    public String updateTripPage(@PathVariable Long tripId,
                                 ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        modelMap.put("trip", tripService.getById(tripId, user));

        return "editTrip";
    }

    @PostMapping("/trips/{tripId}/comments")
    public ResponseEntity createComment(@PathVariable Long tripId, String text, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        TripComment tripComment = TripComment.builder()
                .text(text)
                .trip(Trip.builder().id(tripId).build())
                .commentator(user)
                .build();
        return ResponseEntity.ok(commentService.saveComment(tripComment));
    }

    @PostMapping("/trips/{tripId}/comments/{commentId}")
    public ResponseEntity createComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/trips/{tripId}")
    public ResponseEntity updateProfile(@RequestBody TripForm tripForm,
                                        @PathVariable Long tripId,
                                        HttpServletRequest request) {
        tripService.updateTrip(tripForm, tripId, userService.getCurrentUser(request));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/trips/{tripId}")
    public ResponseEntity updateProfile(@PathVariable Long tripId, HttpServletRequest request) {
        tripService.deleteTripById(tripId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/trips")
    public String myTripsPage(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        List<TripDto> trips = tripService.getTripsByUser(user);
        modelMap.put("activeTrips", trips.stream().filter(trip -> trip.getDate().isAfter(LocalDateTime.now())).collect(Collectors.toList()));
        modelMap.put("expiredTrips", trips.stream().filter(trip -> trip.getDate().isBefore(LocalDateTime.now())).collect(Collectors.toList()));
        return "MyTrips";
    }

    @GetMapping("/trips/booked")
    public String bookedTripsPage(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        List<TripDto> bookedTrips = tripService.getBookedByUser(user);
        modelMap.put("trips", bookedTrips);
        return "bookedTrips";
    }

    @GetMapping("/trips/search")
    public String searchtripsPage(@RequestParam Map<String, String> searchParameters, ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        List<TripDto> trips = tripService.getTripsWithParameters(user, searchParameters);
        modelMap.put("trips", trips);
        return "search";

    }
}
