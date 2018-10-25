package ru.itis.trip.servlets;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.TripService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripByIdServlet extends HttpServlet {
    TripService tripService;
    CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
        commentService = (CommentService) context.getAttribute("commentService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, Object> root = new HashMap<>();
        Long id = getId(request);
        Trip trip = tripService.getById(id);
        List<TripComment> comments = commentService.getComments(trip);
        root.put("trip",trip);
        root.put("comments",comments);
        RenderHelper.render(getServletContext(),response,"Trip.ftl",root);
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/trips/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }
}