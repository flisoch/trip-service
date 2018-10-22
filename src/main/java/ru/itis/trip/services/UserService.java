package ru.itis.trip.services;

import ru.itis.trip.entities.User;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    User signUp(ProfileForm profileForm);
    User getCurrentUser(HttpServletRequest request);
    User signIn(LoginForm loginForm);

    void authorize(User current_user, HttpServletRequest request, HttpServletResponse response);
}
