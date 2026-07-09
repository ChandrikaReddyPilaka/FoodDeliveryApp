package com.foodapp.servlet;

import com.foodapp.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("name");
        String password = request.getParameter("password");

        String query = "SELECT * FROM users WHERE name = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("name", username);

                    // NEW: also store the actual user_id - needed later so
                    // PlaceOrderServlet knows WHICH user is placing the order.
                    // The "name" attribute alone isn't enough for that, since
                    // the orders table needs a real user_id foreign key.
                    session.setAttribute("userId", rs.getInt("user_id"));

                    response.sendRedirect("home.jsp");
                } else {
                    request.setAttribute("error", "Invalid username or password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error, please try again later");
            request.getRequestDispatcher("login.html").forward(request, response);
   }
   }
   }