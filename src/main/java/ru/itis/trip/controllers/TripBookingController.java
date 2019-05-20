package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.trip.entities.User;
import ru.itis.trip.dto.RequestDto;
import ru.itis.trip.forms.RequestForm;
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
    public ResponseEntity acceptOrDenyRequest(@PathVariable Long requestId,
                                              @RequestBody RequestForm requestForm,
                                              HttpServletRequest request) {
        tripService.acceptOrDenyRequest(requestId, requestForm.isAccepted());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity acceptOrDenyRequest(@PathVariable Long requestId,
                                              HttpServletRequest request) {
        tripService.deleteRequestById(requestId);
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

        modelMap.put("requests_to_me", requests.stream().filter(r -> r.getTrip().getIniciator().getId().equals(user.getId())).collect(Collectors.toList()));
        modelMap.put("requests_from_me", requests.stream().filter(r -> r.getUser().getId().equals(user.getId())).collect(Collectors.toList()));
        return "requests";
    }
}
