package ru.itis.trip.servlets;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
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

        List<Trip> trips = tripService.getTripsWithParameters(request);
       /* HashMap<String, Object> root = new HashMap<>();
        root.put("user",userService.getCurrentUser(request));
        root.put("trips",trips);
        RenderHelper.render(getServletContext(),response,"Trips.ftl",root);*/
        User user = userService.getCurrentUser(request);
        request.setAttribute("user",user);
        request.setAttribute("trips",trips);
        System.out.println(trips);
        request.getRequestDispatcher("/jsp/search.jsp").forward(request, response);
    }
}
