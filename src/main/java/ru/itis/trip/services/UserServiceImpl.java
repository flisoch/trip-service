package ru.itis.trip.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trip.dao.UserDao;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;
import ru.itis.trip.forms.RegistrationForm;

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

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> signUp(RegistrationForm registrationForm) {
        User user = User.builder()
                .email(registrationForm.getEmail())
                .hashedPassword(hash(registrationForm.getPassword()))
                .username(registrationForm.getUsername())
                .build();
        User userCandidate = userDao.create(user);
        return Optional.ofNullable(userCandidate);
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("current_user");
        if(user == null){
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
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

        }
        return user;
    }

    @Override
    public Optional<User> signIn(LoginForm loginForm) {

        User user = userDao.getByUsername(loginForm.getUsername()).orElse(null);

        if (user != null && user.getHashedPassword().equals(hash(loginForm.getPassword()))) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public void authorize(User current_user, HttpServletRequest request) {

        request.getSession().setAttribute("current_user",current_user);

    }

    @Override
    public void remember(User current_user, HttpServletResponse response){
        addToken(current_user, response);
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

    @Override
    public User getUserByUsername(String username) {
        return userDao.getByUsername(username).orElse(null);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<User> user = userDao.read(id);
        return user.map(UserDto::from);
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

    private void addToken(User user, HttpServletResponse response) {
        String token = createToken(user.getUsername());
        Cookie cookie = new Cookie("remember_me", token);
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);
        userDao.addToken(user, token);
    }
}
