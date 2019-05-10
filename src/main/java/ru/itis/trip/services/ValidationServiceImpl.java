package ru.itis.trip.services;

import org.springframework.stereotype.Component;
import ru.itis.trip.entities.forms.LoginForm;
import ru.itis.trip.entities.forms.ProfileForm;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateRegForm(ProfileForm form, HttpServletResponse response) {
        validateUsername(form.getUsername(),response);
        validatePassword(form.getPassword(),response);
    }

    @Override
    public void validateUsername(String username, HttpServletResponse response) {
        Pattern pattern = Pattern.compile("[a-z][a-z0-9_]{4,15}",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            try {
                response.sendError(403, "username must consist of letters or digits and start with letter");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void validatePassword(String password, HttpServletResponse response) {
        Pattern pattern = Pattern.compile("[a-z0-9!@#$%^&*-=_+?.,]{3,30}",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            try {
                response.sendError(403, "password must consist of lettert or digits or !@#$%^&*-=_+?., symbols");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void validateLoginForm(LoginForm loginForm, HttpServletResponse response) {
        validateUsername(loginForm.getUsername(), response);
        validatePassword(loginForm.getPassword(), response);
    }
}
