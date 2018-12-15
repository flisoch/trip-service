package ru.itis.trip.contextListeners;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.trip.dao.TripCommentDao;
import ru.itis.trip.dao.TripDao;
import ru.itis.trip.dao.UserDao;
import ru.itis.trip.helpers.DbConnectionConfig;
import ru.itis.trip.helpers.DbDataSource;
import ru.itis.trip.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class TripServiceListener implements ServletContextListener {

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        TripService tripService = (TripService) applicationContext.getBean("tripService");
        CommentService commentService = (CommentService) applicationContext.getBean("commentService");

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("tripService", tripService);
        context.setAttribute("commentService", commentService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
