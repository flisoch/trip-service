package ru.itis.trip.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

/*
POST /trips -create new trip
PATCH /trips{id}   -edit the trip
DELETE /trips/{id}
GET /trips/{id} -get the trip
 */
public class TripsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("trips");
    }
}
