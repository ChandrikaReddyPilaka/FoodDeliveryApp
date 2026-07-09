package com.foodapp.dao;

import com.foodapp.model.OrderItem;
import java.util.List;

/**
 * OrderItemDAO interface - defines WHAT operations are possible on the order_items table.
 * OrderItemDAOImpl provides the actual JDBC code.
 */
public interface OrderItemDAO {

    boolean addOrderItem(OrderItem orderItem);

    OrderItem getOrderItemById(int orderItemId);

    // An order confirmation / order history page shows the items
    // belonging to ONE order, not the entire order_items table.
    List<OrderItem> getOrderItemsByOrderId(int orderId);

    boolean deleteOrderItem(int orderItemId);
}