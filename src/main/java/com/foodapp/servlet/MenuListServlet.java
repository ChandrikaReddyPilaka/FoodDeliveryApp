package com.foodapp.servlet;

import com.foodapp.dao.MenuDAO;
import com.foodapp.dao.impl.MenuDAOImpl;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Fetches menu items for ONE restaurant and forwards them to menu.jsp.
 * URL: http://localhost:8080/FoodDeliveryApp/menu?restaurantId=1
 *
 * Notice this reads a QUERY PARAMETER (?restaurantId=1), not a form
 * submission - this is how a "click to view details" link works,
 * as opposed to a submitted <form>.
 */
@WebServlet("/menu")
public class MenuListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getParameter() works the same way whether the value comes from
        // a form field OR a URL query string like ?restaurantId=1 -
        // both are just "parameters" as far as the servlet is concerned.
        String restaurantIdParam = request.getParameter("restaurantId");

        // Parameters always arrive as String - must convert to int manually.
        int restaurantId = Integer.parseInt(restaurantIdParam);

        MenuDAO menuDAO = new MenuDAOImpl();
        List<Menu> menuList = menuDAO.getMenuByRestaurantId(restaurantId);

        request.setAttribute("menuList", menuList);
        request.setAttribute("restaurantId", restaurantId);

        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
}