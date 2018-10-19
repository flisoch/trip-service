package main.java.ru.itis.trip.servlets;

import java.io.IOException;
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

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("profile");
    }
}
