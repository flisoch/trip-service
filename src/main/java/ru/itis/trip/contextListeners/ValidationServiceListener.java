package ru.itis.trip.contextListeners;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        ValidationService validationService = (ValidationService) applicationContext.getBean("validationService");

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("validationService", validationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
