package com.foodapp.dao;

import com.foodapp.model.Order;
import java.util.List;

/**
 * OrderDAO interface - defines WHAT operations are possible on the orders table.
 * OrderDAOImpl provides the actual JDBC code.
 */
public interface OrderDAO {

    boolean addOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    // A "My Orders" page shows ONE user's order history, not everyone's.
    List<Order> getOrdersByUserId(int userId);

    boolean updateOrderStatus(int orderId, String status);

    boolean deleteOrder(int orderId);
}