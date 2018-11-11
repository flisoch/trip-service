package ru.itis.trip.servlets;

import ru.itis.trip.entities.Request;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.helpers.RenderHelper;
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

public class TripRequests extends HttpServlet {

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

        HashMap<String,List<Request>> requests = tripService.getRequsets(user);
        /*HashMap<String, Object> root = new HashMap<>();
        root.put("requests",requests);
        root.put("user",user);
        RenderHelper.render(getServletContext(),response,"Requests.ftl",root);*/
        request.setAttribute("requests_to_me", requests.getOrDefault("to_me", new ArrayList<>()));
        request.setAttribute("requests_from_me", requests.getOrDefault("from_me", new ArrayList<>()));
        request.getRequestDispatcher("/jsp/requests.jsp").forward(request, response);
    }
}
