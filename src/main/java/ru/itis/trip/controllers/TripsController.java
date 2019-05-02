package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.TripDto;
import ru.itis.trip.forms.NewTripForm;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TripsController {
    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @GetMapping("/trips/new")
    public String newTripPage(ModelMap modelMap, HttpServletRequest request){
        modelMap.put("user", userService.getCurrentUser(request));
        return "newTrip";
    }

    @PostMapping("/trips")
    public String createTrip(NewTripForm form, HttpServletRequest request){
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



}
