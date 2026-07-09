package com.foodapp.servlet;

import com.foodapp.dao.OrderDAO;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.dao.impl.OrderDAOImpl;
import com.foodapp.dao.impl.OrderItemDAOImpl;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Converts the session Cart into REAL database records: one Order row,
 * plus one OrderItem row per cart line. This is the moment temporary
 * session data becomes permanent database data.
 * URL: http://localhost:8080/FoodDeliveryApp/placeOrder
 */
@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Read who's logged in and what's in their cart - both come
        // from the session, set earlier by LoginServlet and CartServlet.
        Object userIdObj = session.getAttribute("userId");
        Cart cart = (Cart) session.getAttribute("cart");

        // Basic safety checks - don't try to place an order for someone
        // who isn't logged in, or with an empty cart.
        if (userIdObj == null) {
            response.sendRedirect("login.html");
            return;
        }
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        int userId = (Integer) userIdObj;

        OrderDAO orderDAO = new OrderDAOImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        // STEP 1: Create the Order row first. We need its auto-generated
        // order_id BEFORE we can insert any OrderItems, since each
        // OrderItem needs to reference which order it belongs to.
        Order order = new Order(userId, cart.getRestaurantId(), cart.getTotal(), "PLACED", null);
        boolean orderAdded = orderDAO.addOrder(order);

        if (!orderAdded) {
            request.setAttribute("error", "Could not place order. Please try again.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        // order.getOrderId() is now filled in automatically - this is the
        // RETURN_GENERATED_KEYS behavior from OrderDAOImpl we discussed earlier.
        int newOrderId = order.getOrderId();

        // STEP 2: Insert one OrderItem row for every item that was in the cart.
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem(
                    newOrderId,
                    cartItem.getItemId(),
                    cartItem.getQuantity(),
                    cartItem.getPrice()
            );
            orderItemDAO.addOrderItem(orderItem);
        }

        // STEP 3: Order successfully placed - empty the cart so the next
        // visit to /cart doesn't still show these already-ordered items.
        cart.clear();

        // Pass the new order id to the confirmation page.
        request.setAttribute("orderId", newOrderId);
        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
    }
}