package ru.itis.trip.servlets;

import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.ProfileForm;
import ru.itis.trip.forms.TripForm;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;


//GET /trips/{id}/edit
public class EditTripServlet extends HttpServlet {
    UserService userService;
    TripService tripService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
        userService = (UserService) context.getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TripForm tripForm = TripForm.builder()
                .arrivalPoint(request.getParameter("destination"))
                .seatsNumber(Integer.valueOf(request.getParameter("seats")))
                .departurePoint(request.getParameter("departure"))
                .info(request.getParameter("info"))
                .date(request.getParameter("date"))
                .build();
        tripService.updateTrip(request, tripForm);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        Long id = getId(request);
        User currentUser = (User)request.getSession().getAttribute("current_user");

        Trip trip = tripService.getById(id);

        if(!trip.getIniciator().getId().equals(currentUser.getId())){
            response.sendRedirect("/trips");
            return;
        }
        /*root.put("user",userService.getCurrentUser(request));
        root.put("trip",trip);
        RenderHelper.render(getServletContext(),response,"EditTrip.ftl",root);*/
        request.setAttribute("trip",trip);
        request.getRequestDispatcher("/jsp/editTrip.jsp").forward(request, response);
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/trips/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        Long id = null;
        if(matcher.find()){
            id = Long.valueOf(matcher.group(1));
        }

        return id;
    }
}
