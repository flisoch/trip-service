package ru.itis.trip.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class LogOutServlet extends HttpServlet {

    private void deleteRememberMeCookie(HttpServletRequest request, HttpServletResponse response) {

        for (Cookie cookie : request.getCookies()) {

            if (cookie.getName().equals("remember_me")) {

                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        deleteRememberMeCookie(request,response);

        response.sendRedirect("/login");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Todo:remove from here when change 'logout' type on page from link to button
        request.getSession().invalidate();
        deleteRememberMeCookie(request,response);

        response.sendRedirect("/login");
    }
}
