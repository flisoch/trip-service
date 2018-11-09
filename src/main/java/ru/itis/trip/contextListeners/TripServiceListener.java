package ru.itis.trip.contextListeners;

import lombok.SneakyThrows;
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

        Class.forName("org.postgresql.Driver");
        Connection connection = DbConnectionConfig.getConnection();
        DataSource dataSource = DbDataSource.getDataSource();

        TripDao tripDao = new ru.itis.trip.dao.implementation.TripDao(dataSource);
        TripService tripService = new TripServiceImpl(tripDao);

        TripCommentDao tripCommentDao = new ru.itis.trip.dao.implementation.TripCommentDao(connection);
        CommentService commentService = new CommentServiceImpl(tripCommentDao);

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("tripService", tripService);
        context.setAttribute("commentService", commentService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
