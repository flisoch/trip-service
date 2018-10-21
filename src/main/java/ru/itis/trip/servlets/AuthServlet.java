package ru.itis.trip.servlets;

import ru.itis.trip.entities.User;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.forms.ProfileForm;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class AuthServlet extends HttpServlet {

    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginForm loginForm = LoginForm.builder()
                .username(username)
                .password(password)
                .build();
        User current_user = userService.signIn(loginForm);
        userService.authorize(request,current_user);
        if(current_user != null){
            response.sendRedirect("/profile");
            return;
        }
        response.sendRedirect("/auth");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RenderHelper.render(getServletContext(),response,"Auth.ftl",new HashMap<>());
    }
}
