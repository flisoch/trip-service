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
        Long id = getId(request);
        User user = userService.getUserById(id);
        root.put("user",user);
        RenderHelper.render(getServletContext(),response,"ProfileById.ftl",root);
    }

    private Long getId(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/profile/([1-9][0-9]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }
}
