package main.java.ru.itis.trip.servlets;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;

//GET /profile/{id}/comments
//POST /profile/{id}/comments
public class ProfileCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("profile Comment");
    }
}
