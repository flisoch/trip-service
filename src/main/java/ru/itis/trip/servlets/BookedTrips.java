package ru.itis.trip.servlets;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookedTrips extends HttpServlet {
    TripService tripService;
    UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        List<Trip> bookedTrips = tripService.getBookedByUser(user);
        request.setAttribute("trips", bookedTrips);
        request.getRequestDispatcher("/jsp/bookedTrips.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}