package ru.itis.trip.servlets;

import ru.itis.trip.entities.User;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.HashMap;
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        User user;
        String id = getId(request);
        if(id == null){
            user = (User)request.getSession().getAttribute("current_user");
        }
        else{
            user = userService.getUserById(Long.parseLong(id));
        }
        if(user == null){
            response.sendRedirect("/trips");
            return;
        }
        /*root.put("profile",user);
        root.put("user", userService.getCurrentUser(request));
        RenderHelper.render(getServletContext(),response,"ProfileById.ftl",root);*/
        request.setAttribute("user",user);
        request.getRequestDispatcher("/jsp/MyProfile.jsp").forward(request, response);
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
