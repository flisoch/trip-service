package ru.itis.trip.services;


import ru.itis.trip.dao.UserDao;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;

import javax.servlet.http.HttpServletRequest;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User signUp(ProfileForm profileForm) {
        User user = User.builder()
                .email(profileForm.getEmail())
                .hashedPassword(profileForm.getPassword())
                .username(profileForm.getUsername())
                .build();
        if(userDao.create(user)){
            return user;
        }
        return null;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        return (User)request.getSession().getAttribute("current_user");
    }

    @Override
    public User signIn(LoginForm loginForm) {

        User user = userDao.getByUsername(loginForm.getUsername()).get();

        if (user.getHashedPassword().equals(loginForm.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public void authorize(HttpServletRequest request, User current_user) {

        request.getSession().setAttribute("current_user",current_user);

        /*if(request.getParameter("remember_me") != null){

            Cookie cookie = new Cookie("remember_me",  current_user.getName());
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);}*/
    }
}
