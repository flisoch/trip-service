package ru.itis.trip.servlets;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

/*
POST /trips -create new trip
PATCH /trips{id}   -edit the trip
DELETE /trips/{id}
GET /trips/{id} -get the trip
 */
public class TripsServlet extends HttpServlet {

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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, Object> root = new HashMap<>();
        List<Trip> trips = tripService.getAllTrips();
        root.put("trips",trips);
        root.put("user",userService.getCurrentUser(request));
        RenderHelper.render(getServletContext(),response,"Trips.ftl",root);
    }
}