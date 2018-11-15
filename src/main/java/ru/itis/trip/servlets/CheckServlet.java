package ru.itis.trip.servlets;

import com.google.gson.Gson;
import ru.itis.trip.entities.User;
import ru.itis.trip.forms.LoginForm;
import ru.itis.trip.helpers.RenderHelper;
import ru.itis.trip.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckServlet extends HttpServlet {

    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserByUsername(req.getParameter("username"));
        if(user != null){
            resp.getWriter().write(new Gson().toJson(false));
        }
    }

    /*private String getParameter(HttpServletRequest request) {
        Pattern compile = Pattern.compile("/check/([a-zA-z]*)");
        Matcher matcher = compile.matcher(request.getRequestURI());
        String parameter = null;
        if(matcher.find()){
            parameter = matcher.group(1);
        }
        return parameter;
    }*/
}
