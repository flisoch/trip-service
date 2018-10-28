package ru.itis.trip.servlets;

import ru.itis.trip.entities.User;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.ServletException;


public class AcceptTripServlet extends HttpServlet {

    TripService tripService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
        userService = (UserService) context.getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long userId = Long.parseLong(request.getParameter("user_id"));
        Long tripId = Long.parseLong(request.getParameter("trip_id"));
        tripService.acceptRequest(userId,tripId);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("reject trip");
    }
}
