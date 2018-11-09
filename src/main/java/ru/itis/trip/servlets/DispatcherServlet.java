package ru.itis.trip.servlets;

import ru.itis.trip.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    private List<Pair<Pattern, HttpServlet>> map;

    @Override
    public void init() {
        
        map = new LinkedList<>();
        addPattern("/trips/new", new NewTripServlet());
        addPattern("/trips/([1-9][0-9]*)/edit", new EditTripServlet());
        addPattern("/trips/([1-9][0-9]*)", new TripByIdServlet());
        addPattern("/trips", new TripsServlet());
        addPattern("/trips/([1-9][0-9]*)/apply", new ApplyTripServlet());

        addPattern("/requests/reject", new RejectTripServlet());
        addPattern("/requests/accept", new AcceptTripServlet());

        addPattern("/trips/([1-9][0-9]*)/comments", new TripCommentServlet());
        addPattern("/profile/([1-9][0-9]*)/comments", new ProfileCommentServlet());
        addPattern("/trips/search*",new TripSearchServlet());

        addPattern("/profile/edit", new EditProfileServlet());
        addPattern("/profile/([1-9][0-9]*)", new ProfileServlet());
        addPattern("/profile", new ProfileServlet());
        addPattern("/profile/requests", new TripRequests());
        addPattern("/profile/trips", new ProfileTrips());

        addPattern("/registration",new RegistrationServlet());
        addPattern("/auth", new AuthServlet());
        addPattern("/logout", new LogOutServlet());
        addPattern("/check",new CheckServlet());

    }

    private void addPattern(String regex, HttpServlet instance) {
        map.add(new Pair<>(Pattern.compile(regex), instance));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        for (Pair<Pattern, HttpServlet> pair : map) {
            if (pair.getKey().matcher(uri).matches()) {
                pair.getValue().init(getServletConfig());
                pair.getValue().service(req, resp);
                return;
            }
        }
        resp.sendError(404);
    }
}