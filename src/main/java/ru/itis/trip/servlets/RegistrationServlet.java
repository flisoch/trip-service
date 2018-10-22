package ru.itis.trip.servlets;

import ru.itis.trip.entities.User;
import ru.itis.trip.forms.ProfileForm;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class RegistrationServlet extends javax.servlet.http.HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        ProfileForm form = ProfileForm.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
        //Todo:form validation
        User user = userService.signUp(form);
        if(user != null){
            userService.authorize(user, request, response);
            response.sendRedirect("/profile");
            return;
        }
        response.sendRedirect("/registration");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RenderHelper.render(getServletContext(),response,"Registration.ftl",new HashMap<>());
    }
}
