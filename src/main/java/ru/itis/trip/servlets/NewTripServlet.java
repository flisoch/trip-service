package ru.itis.trip.servlets;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.TripService;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;


public class NewTripServlet extends HttpServlet {

    TripService tripService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = request.getParameter("destination");
        String departure = request.getParameter("departure");
        String info = request.getParameter("info");
        String freeSeats = request.getParameter("seats");
        User user = (User)request.getSession().getAttribute("current_user");
        String dateTime = request.getParameter("time_to");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//"yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        Date date;
        Long epoch = 0L;
        try {
            date = dateFormat.parse(dateTime);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Trip trip = Trip.builder()
                .arrivalPoint(destination)
                .departurePoint(departure)
                .freeSeats(Integer.parseInt(freeSeats))
                .date(epoch)
                .info(info)
                .iniciator(user)
                .build();
        tripService.createTrip(trip);
        response.sendRedirect("/trips/"+trip.getId()+"/edit");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        RenderHelper.render(getServletContext(),response,"NewTrip.ftl",root);
    }
}
