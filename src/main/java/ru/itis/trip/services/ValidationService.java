package ru.itis.trip.services;

import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;

import javax.servlet.http.HttpServletResponse;

public interface ValidationService {
    void validateRegForm(ProfileForm form, HttpServletResponse response);
    void validateUsername(String username, HttpServletResponse response);
    void validatePassword(String password, HttpServletResponse response);

    void validateLoginForm(LoginForm loginForm, HttpServletResponse response);
}
