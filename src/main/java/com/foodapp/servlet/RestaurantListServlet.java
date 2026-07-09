package com.foodapp.servlet;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.dao.impl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Fetches all restaurants and forwards them to restaurants.jsp for display.
 * URL: http://localhost:8080/FoodDeliveryApp/restaurants
 */
@WebServlet("/restaurants")
public class RestaurantListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

        // request.setAttribute() passes data from servlet -> JSP.
        // Unlike getParameter() (which reads data FROM the browser),
        // setAttribute()/getAttribute() is how servlets and JSPs share
        // data with EACH OTHER on the server side.
        request.setAttribute("restaurantList", restaurantList);

        // RequestDispatcher.forward() hands off control to the JSP page,
        // WITHOUT the browser doing a new request (URL stays /restaurants,
        // not /restaurants.jsp) - the attribute we just set is still
        // available because it's the SAME request object.
        request.getRequestDispatcher("restaurants.jsp").forward(request, response);
    }
}