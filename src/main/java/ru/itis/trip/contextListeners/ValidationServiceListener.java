package ru.itis.trip.contextListeners;

import lombok.SneakyThrows;
import ru.itis.trip.dao.TripCommentDao;
import ru.itis.trip.dao.TripDao;
import ru.itis.trip.dao.UserDao;
import ru.itis.trip.helpers.DbConnectionConfig;
import ru.itis.trip.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class ValidationServiceListener implements ServletContextListener {

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ValidationService validationService = new ValidationServiceImpl();

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("validationService", validationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
