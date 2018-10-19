package main.java.ru.itis.trip.servlets;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;

//POST /trips/{id}/reject
public class RejectTripServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("reject trip");
    }
}
