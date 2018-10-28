package ru.itis.trip.servlets;

import ru.itis.trip.entities.User;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.TripService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

//POST /trips/{id}/apply
public class ApplyTripServlet extends HttpServlet {


    TripService tripService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long tripId = getId(request);
        User user = (User) request.getSession().getAttribute("current_user");

        tripService.sendApply(tripId,user.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("applytrip");
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/trips/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }
}
