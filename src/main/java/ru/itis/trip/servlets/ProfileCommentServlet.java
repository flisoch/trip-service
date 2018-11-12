package ru.itis.trip.servlets;

import com.google.gson.Gson;
import ru.itis.trip.entities.Trip;
import ru.itis.trip.entities.TripComment;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.TripService;
import ru.itis.trip.services.UserCommentService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.ServletException;

//GET /profile/{id}/comments
//POST /profile/{id}/comments
public class ProfileCommentServlet extends HttpServlet {
    TripService tripService;
    UserCommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        tripService = (TripService) context.getAttribute("tripService");
        commentService = (UserCommentService) context.getAttribute("userCommentService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        String commentId = request.getParameter("comment_id");
        if (text == null && commentId!= null){
            commentService.deleteComment(Long.parseLong(commentId));
            return;
        }
        User user = (User)request.getSession().getAttribute("current_user");
        Long dateTime = System.currentTimeMillis();
        UserComment comment = UserComment.builder()
                .date(dateTime)
                .text(text)
                .commentatee(User.builder().id(getId(request)).build())
                .commentator(user)
                .build();
        commentService.saveComment(comment);
        response.setContentType("text/json");
        response.getWriter().write((new Gson()).toJson(comment));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("profile Comment");
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/profile/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }
}
