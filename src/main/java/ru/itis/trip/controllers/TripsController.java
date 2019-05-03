package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.forms.TripForm;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TripsController {
    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    CommentService commentService;

    @GetMapping("/trips/new")
    public String newTripPage(ModelMap modelMap, HttpServletRequest request){
        modelMap.put("user", userService.getCurrentUser(request));
        return "newTrip";
    }

    @PostMapping("/trips")
    public String createTrip(TripForm form, HttpServletRequest request){
        User user = userService.getCurrentUser(request);
        Trip trip = tripService.createTrip(form, user);
        return "redirect:/trips/"+trip.getId()+"/edit";
    }

    @GetMapping("/trips/{tripId}")
    public String tripPage(@PathVariable Long tripId,
                           ModelMap modelMap, HttpServletRequest request){
        modelMap.put("user", userService.getCurrentUser(request));
        modelMap.put("trip", tripService.getById(tripId));

        return "tripById";
    }

    @GetMapping("/trips/{tripId}/edit")
    public String updateTripPage(@PathVariable Long tripId,
                                 ModelMap modelMap, HttpServletRequest request){
        modelMap.put("user", userService.getCurrentUser(request));
        modelMap.put("trip", tripService.getById(tripId));

        return "editTrip";
    }

    @PostMapping("/trips/{tripId}/comments")
    public ResponseEntity createComment(@PathVariable Long tripId, String text, HttpServletRequest request){
        User user = userService.getCurrentUser(request);
        TripComment tripComment = TripComment.builder()
                .text(text)
                .trip(Trip.builder().id(tripId).build())
                .commentator(user)
                .build();
        return ResponseEntity.ok(commentService.saveComment(tripComment));
    }

    @PostMapping("/trips/{tripId}/comments/{commentId}")
    public ResponseEntity createComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/trips/{tripId}")
    public ResponseEntity updateProfile(@PathVariable Long tripId, TripForm tripForm, HttpServletRequest request){
        tripService.updateTrip(tripForm, tripId, userService.getCurrentUser(request));
        return ResponseEntity.ok().build();
    }
}
