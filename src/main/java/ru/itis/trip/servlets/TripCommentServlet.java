package ru.itis.trip.servlets;

import com.google.gson.Gson;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.TripService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.ServletException;

//GET /trips/{id}/comments
public class TripCommentServlet extends HttpServlet {

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
        String text = request.getParameter("text");
        String commentId = request.getParameter("comment_id");
        if (text == null && commentId!= null){
            commentService.deleteComment(Long.parseLong(commentId));
            return;
        }
        User user = (User)request.getAttribute("user");
        Long dateTime = System.currentTimeMillis();
        TripComment tripComment = TripComment.builder()
                .date(dateTime)
                .text(text)
                .trip(Trip.builder().id(getId(request)).build())
                .commentator(user)
                .build();
        commentService.saveComment(tripComment);
        response.setContentType("text/json");
        response.getWriter().write((new Gson()).toJson(tripComment));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("tripComment");
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/trips/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }

}
