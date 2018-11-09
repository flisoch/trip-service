package ru.itis.trip.helpers;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDataSource {


    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/carservice";
    private static DriverManagerDataSource dataSource;

    public static DriverManagerDataSource getDataSource() {
        if(dataSource == null){
            dataSource = new DriverManagerDataSource();
            dataSource.setUrl(URL);
            dataSource.setUsername(USERNAME);
            dataSource.setPassword(PASSWORD);
        }
        return dataSource;
    }
}
