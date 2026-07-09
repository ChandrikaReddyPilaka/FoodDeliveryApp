package com.foodapp.servlet;

import com.foodapp.model.Cart;
import com.foodapp.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Handles adding items to the cart (POST from menu.jsp's "Add to Cart" form)
 * and displaying the cart (GET when clicking "View Cart").
 * URL: http://localhost:8080/FoodDeliveryApp/cart
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    // doPost() runs when "Add to Cart" form is submitted from menu.jsp
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String itemName = request.getParameter("itemName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

        // getSession() with no arguments creates a new session if one
        // doesn't already exist for this browser - important because
        // a user might add to cart before ever logging in on some sites,
        // though in our app they'll already have a session from login.
        HttpSession session = request.getSession();

        // Look for an existing Cart object in the session first.
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            // First time adding anything - create a fresh Cart and store it.
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        cart.setRestaurantId(restaurantId);   // remember which restaurant this cart belongs to

        CartItem newItem = new CartItem(itemId, itemName, price, quantity);
        cart.addItem(newItem);

        // After adding, send the user to view their cart -
        // sendRedirect (not forward) so refreshing the cart page
        // later doesn't accidentally resubmit the "add" form.
        response.sendRedirect("cart");
    }

    // doGet() runs when visiting /cart directly (e.g. clicking "View Cart" link),
    // and ALSO handles removing an item, via a link like:
    // cart?action=remove&itemId=3
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();   // no items added yet - show an empty cart, not an error
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if ("remove".equals(action)) {
            int itemIdToRemove = Integer.parseInt(request.getParameter("itemId"));
            cart.removeItem(itemIdToRemove);
            // Redirect back to plain /cart (no query string) so refreshing
            // afterward doesn't repeat the removal - same Post/Redirect/Get
            // idea we used for placing orders.
            response.sendRedirect("cart");
            return;
        }

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
