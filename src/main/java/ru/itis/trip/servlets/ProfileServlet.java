package ru.itis.trip.servlets;

import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.HashMap;
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
        RenderHelper.render(getServletContext(),response,"Profile.ftl",new HashMap<>());
    }
}
