package ru.itis.trip.services;

import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.entities.forms.LoginForm;
import ru.itis.trip.entities.forms.ProfileForm;
import ru.itis.trip.entities.forms.RegistrationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface UserService {
    Optional<User> signUp(RegistrationForm registrationForm);
    User getCurrentUser(HttpServletRequest request);
    Optional<User> signIn(LoginForm loginForm);

    void authorize(User current_user, HttpServletRequest request);

    void remember(User current_user, HttpServletResponse response);

    void updateUser(User user, ProfileForm profileForm);

    Optional<UserDto> getUserById(Long id);

    User getUserByUsername(String username);
}
