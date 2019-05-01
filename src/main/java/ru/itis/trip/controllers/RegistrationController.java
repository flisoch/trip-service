package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.RegistrationForm;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(RegistrationForm registrationForm,
                               HttpServletRequest request){

        Optional<User> user = userService.signUp(registrationForm);
        if(user.isPresent()) {
            userService.authorize(user.get(), request);
            return "redirect:/profile";
        }
        else {
            return "redirect:/registration?error='invalid registration form'";
        }

    }
}
