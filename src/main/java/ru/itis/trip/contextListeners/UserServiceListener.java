package ru.itis.trip.contextListeners;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.trip.dao.UserCommentDao;
import ru.itis.trip.dao.UserDao;
import ru.itis.trip.helpers.DbConnectionConfig;
import ru.itis.trip.helpers.DbDataSource;
import ru.itis.trip.services.UserCommentService;
import ru.itis.trip.services.UserCommentServiceImpl;
import ru.itis.trip.services.UserService;
import ru.itis.trip.services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class UserServiceListener implements ServletContextListener {

//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgres";
//    private static final String URL = "jdbc:postgresql://localhost:5432/carservice";


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        UserService userService = (UserService) applicationContext.getBean("userService");
        UserCommentService commentService = (UserCommentService) applicationContext.getBean("userCommentService");


        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("userCommentService", commentService);
        context.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
