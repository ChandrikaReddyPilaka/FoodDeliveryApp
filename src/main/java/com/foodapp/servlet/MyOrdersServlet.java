package com.foodapp.servlet;

import com.foodapp.dao.OrderDAO;
import com.foodapp.dao.impl.OrderDAOImpl;
import com.foodapp.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Fetches the logged-in user's order history and forwards to myOrders.jsp.
 * URL: http://localhost:8080/FoodDeliveryApp/myOrders
 */
@WebServlet("/myOrders")
public class MyOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object userIdObj = session.getAttribute("userId");

        // Same safety check as PlaceOrderServlet - don't show order
        // history to someone who isn't logged in.
        if (userIdObj == null) {
            response.sendRedirect("login.html");
            return;
        }

        int userId = (Integer) userIdObj;

        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orderList = orderDAO.getOrdersByUserId(userId);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("myOrders.jsp").forward(request, response);
    }
}