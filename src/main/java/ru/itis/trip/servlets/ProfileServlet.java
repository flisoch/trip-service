package ru.itis.trip.servlets;

import ru.itis.trip.entities.User;
import ru.itis.trip.entities.UserComment;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.CommentService;
import ru.itis.trip.services.UserCommentService;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

/*
GET /profile
  GET /profile/{id}
  GET /profile/edit
  PATCH /profile
  DELETE /profile
 */

public class ProfileServlet extends HttpServlet {

    private UserService userService;
    private UserCommentService userCommentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        userCommentService = (UserCommentService) context.getAttribute("userCommentService");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        User user;
        List<UserComment> comments;
        String id = getId(request);
        if(id == null || Long.parseLong(id) == userService.getCurrentUser(request).getId()){
            comments = userCommentService.getCommentsByUser(userService.getCurrentUser(request));
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/jsp/MyProfile.jsp").forward(request, response);
        }
        else{
            user = userService.getUserById(Long.parseLong(id));
            comments = userCommentService.getCommentsByUser(user);
            request.setAttribute("comments", comments);
            request.setAttribute("profile",user);
            request.getRequestDispatcher("/jsp/profileById.jsp").forward(request, response);
        }

        /*root.put("profile",user);
        root.put("user", userService.getCurrentUser(request));
        RenderHelper.render(getServletContext(),response,"ProfileById.ftl",root);*/
        
    }

    private String getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/profile/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        String id = null;
        if(matcher.find()){
            id = matcher.group(1);
        }
        return id;
    }
}
