package ru.itis.trip.servlets;

import ru.itis.trip.forms.ProfileForm;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class EditProfileServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfileForm profileForm = ProfileForm.builder()
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .address(request.getParameter("address"))
                .name(request.getParameter("name"))
                .additionalInfo(request.getParameter("bio"))
                .age(Integer.parseInt(request.getParameter("age")))
                .job(request.getParameter("job"))
                .middlename(request.getParameter("middlename"))
                .surname(request.getParameter("lastname"))
                .build();
        userService.updateUser(userService.getCurrentUser(request),profileForm);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RenderHelper.render(getServletContext(),response,"EditProfile.ftl",new HashMap<>());
    }
}
