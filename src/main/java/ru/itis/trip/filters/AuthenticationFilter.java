package ru.itis.trip.filters;

import ru.itis.trip.util.Pair;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        String loginURI = request.getContextPath() + "/auth";
        String regURI = request.getContextPath() + "/registration";

        Pattern staticFilePattern = Pattern.compile("/static/.*(css|js)");
        boolean isStaticFile = staticFilePattern.matcher(requestURI).matches();

        boolean loggedIn = session != null && session.getAttribute("current_user") != null;
        boolean regRequest = requestURI.equals(regURI);
        boolean loginRequest = requestURI.equals(loginURI);


        if (regRequest||loggedIn || isStaticFile || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/auth");
        }
    }

    @Override
    public void destroy() {

    }
}
