package ru.itis.trip.contextListeners;

import ru.itis.trip.helpers.DbConnectionConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

public class UserServiceListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Connection connection = DbConnectionConfig.getConnection();
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("dbConnection", connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
