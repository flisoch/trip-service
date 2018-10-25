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

//GET trips/search?params
public class TripSearchServlet extends HttpServlet {

    TripService tripService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HashMap<String, Object> root = new HashMap<>();
        List<Trip> trips = tripService.getTripsWithParameters(request);
        root.put("trips",trips);
        RenderHelper.render(getServletContext(),response,"Trips.ftl",root);
    }
}
