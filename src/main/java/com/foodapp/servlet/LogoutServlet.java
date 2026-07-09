package com.foodapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Logs the user out by invalidating their session, then redirects to login.
 * URL: http://localhost:8080/FoodDeliveryApp/logout
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        // getSession(false) - the "false" means "don't create a new session
        // if one doesn't already exist." We're logging out, so there's no
        // reason to create a brand new empty session just to immediately
        // do nothing with it.

        if (session != null) {
            // invalidate() wipes ALL session data - name, userId, cart,
            // everything. This is what actually "logs the user out":
            // the next request will have no memory of who they were.
            session.invalidate();
        }

        response.sendRedirect("login.html");
    }
}
