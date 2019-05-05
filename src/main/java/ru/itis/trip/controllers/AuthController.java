package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.forms.LoginForm;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/auth")
    public String getLoginPage(){
        return "auth";
    }

    @PostMapping("/auth")
    public String authenticate(LoginForm loginForm,
                               HttpServletRequest request, HttpServletResponse response){
        Optional<User> current_user = userService.signIn(loginForm);
        current_user.ifPresent(user -> userService.authorize(user,request));

        if(!current_user.isPresent()){
            return "redirect:/auth?error='invalid credentials'";
        }

        if(request.getParameter("remember_me") != null) {
            userService.remember(current_user.get(), response);
        }
        return "redirect:/profile";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        userService.deleteRememberMeCookie(request,response);
        return "redirect:/auth";
    }
}
