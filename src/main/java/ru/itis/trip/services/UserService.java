package ru.itis.trip.services;

import ru.itis.trip.entities.User;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User signUp(ProfileForm profileForm);
    User getCurrentUser(HttpServletRequest request);
    User signIn(LoginForm loginForm);

    void authorize(HttpServletRequest request, User current_user);
}
