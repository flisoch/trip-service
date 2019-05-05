package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.forms.RequestForm;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TripBookingController {

    @Autowired
    TripService tripService;

    @Autowired
    UserService userService;

    @PutMapping("/requests/{requestId}")
    public ResponseEntity acceptOrDenyRequest(@PathVariable Long requestId, @RequestBody RequestForm accepted){
        tripService.acceptOrDenyRequest(requestId, accepted.isAccepted());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/trips/{tripId}/requests")
    public ResponseEntity applyForATrip(@PathVariable Long tripId, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        tripService.sendApply(tripId,user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/requests")
    protected String requestsPage(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        HashMap<String, List<Request>> requests = tripService.getRequsets(user);

        modelMap.put("requests_to_me", requests.getOrDefault("to_me", new ArrayList<>()));
        modelMap.put("requests_from_me", requests.getOrDefault("from_me", new ArrayList<>()));
        return "requests";
    }
}
