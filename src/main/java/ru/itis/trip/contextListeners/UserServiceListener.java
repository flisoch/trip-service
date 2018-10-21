package ru.itis.trip.contextListeners;

import lombok.SneakyThrows;
import ru.itis.trip.dao.UserDao;
import ru.itis.trip.helpers.DbConnectionConfig;
import ru.itis.trip.services.UserService;
import ru.itis.trip.services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class UserServiceListener implements ServletContextListener {

//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgres";
//    private static final String URL = "jdbc:postgresql://localhost:5432/carservice";


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Class.forName("org.postgresql.Driver");
        Connection connection = DbConnectionConfig.getConnection();

        UserDao userDao = new ru.itis.trip.dao.implementation.UserDao(connection);
        UserService userService = new UserServiceImpl(userDao);

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
