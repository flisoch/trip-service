package ru.itis.trip.services;


import ru.itis.trip.dao.UserDao;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User signUp(ProfileForm profileForm) {
        User user = User.builder()
                .email(profileForm.getEmail())
                .hashedPassword(hash(profileForm.getPassword()))
                .username(profileForm.getUsername())
                .build();
        if(userDao.create(user)){
            return user;
        }
        return null;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user == null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies){
                if(cookie.getName().equals("remember_me")){
                    Optional<User> userDb = userDao.getByToken(cookie.getValue());
                    if(userDb.isPresent()){
                        user = userDb.get();
                        request.getSession().setAttribute("current_user", user);
                    }
                }
            }
        }
        return user;
    }

    @Override
    public User signIn(LoginForm loginForm) {

        User user = userDao.getByUsername(loginForm.getUsername()).get();

        if (user.getHashedPassword().equals(hash(loginForm.getPassword()))) {
            return user;
        }

        return null;
    }

    @Override
    public void authorize(User current_user, HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("current_user",current_user);

        if(request.getParameter("remember_me") != null) {
            addToken(current_user, response);
        }
    }

    @Override
    public void updateUser(User user, ProfileForm profileForm) {
        String password = profileForm.getPassword();
        if(!password.equals("")){
            user.setHashedPassword(hash(password));
        }
        user.setAddress(profileForm.getAddress());
        user.setUsername(profileForm.getUsername());
        user.setAge(profileForm.getAge());
        user.setLastname(profileForm.getSurname());
        user.setMiddlename(profileForm.getMiddlename());
        user.setJob(profileForm.getJob());
        user.setAdditionalInfo(profileForm.getAdditionalInfo());
        user.setName(profileForm.getName());
        userDao.update(user);
    }

    private String createToken(String username) {
        String token = username + new Date().toString();
        return hash(token);
    }

    private String hash(String word) {
        final byte[] salt = new byte[]{-26, 107, -28, 36, 90, -64, -119, 70, -80, 115, -84, -38, -19, -123, -88, -70};

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt);
        byte[] hashedWord = md.digest(word.getBytes(StandardCharsets.UTF_8));

//        return new String(hashedWord, StandardCharsets.US_ASCII).replaceAll("\u0000", "");
        try {
            return URLEncoder.encode(new String(hashedWord,StandardCharsets.US_ASCII), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void addToken(User user, HttpServletResponse response) {
        String token = createToken(user.getUsername());
        Cookie cookie = new Cookie("remember_me", token);
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);
        userDao.addToken(user, token);
    }
}
