package ru.itis.trip.services;


import ru.itis.trip.dao.UserDao;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.ProfileForm;

public class UserServiceImpl implements UserService {

    private UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void signUp(ProfileForm profileForm) {
        User user = User.builder()
                .email(profileForm.getEmail())
                .hashedPassword(profileForm.getPassword())
                .name(profileForm.getName())
                .build();
        userDao.create(user);
    }
}
