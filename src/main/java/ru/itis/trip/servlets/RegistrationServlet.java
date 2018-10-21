package ru.itis.trip.servlets;

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
        System.out.println(userService);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("username");
        ProfileForm form = ProfileForm.builder()
                .email(email)
                .password(password)
                .name(firstName)
                .build();

        userService.signUp(form);
        response.sendRedirect("/auth");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RenderHelper.render(getServletContext(),response,"Registration.ftl",new HashMap<>());
    }
}
