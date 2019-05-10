package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.RequestDto;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.entities.forms.RequestForm;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TripBookingController {

    @Autowired
    TripService tripService;

    @Autowired
    UserService userService;

    @PutMapping("/requests/{requestId}")
    public ResponseEntity acceptOrDenyRequest(@PathVariable Long requestId, @RequestBody RequestForm accepted) {
        tripService.acceptOrDenyRequest(requestId, accepted.isAccepted());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/trips/{tripId}/requests")
    public ResponseEntity applyForATrip(@PathVariable Long tripId, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        tripService.sendApply(tripId, user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/requests")
    protected String requestsPage(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        List<RequestDto> requests = tripService.getRequsets(user);

        modelMap.put("user", UserDto.from(user));
        modelMap.put("requests_to_me", requests.stream().filter(r -> r.getTrip().getIniciator().getId().equals(user.getId())).collect(Collectors.toList()));
        modelMap.put("requests_from_me", requests.stream().filter(r -> r.getUser().getId().equals(user.getId())).collect(Collectors.toList()));
        return "requests";
    }
}
