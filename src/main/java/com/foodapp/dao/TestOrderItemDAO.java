package com.foodapp.dao;

import com.foodapp.dao.impl.OrderItemDAOImpl;
import com.foodapp.model.OrderItem;

import java.util.List;

/**
 * TestOrderItemDAO - a plain class with a main() method to manually test
 * OrderItemDAOImpl. Right-click this file -> Run As -> Java Application.
 */
public class TestOrderItemDAO {

    public static void main(String[] args) {

        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        // ---------------------------------------------------------
        // TEST 1: Get order items for ONE order (order_id = 1)
        // This uses your seed data - order 1 already has an item in it.
        // ---------------------------------------------------------
        List<OrderItem> items = orderItemDAO.getOrderItemsByOrderId(1);
        System.out.println("Order items for order_id=1: " + items.size());
        for (OrderItem oi : items) {
            System.out.println(oi);
        }

        // ---------------------------------------------------------
        // TEST 2: Get a specific order item by its own ID
        // ---------------------------------------------------------
        OrderItem fetched = orderItemDAO.getOrderItemById(1);
        System.out.println("Order item with id=1: " + fetched);

        // ---------------------------------------------------------
        // TEST 3: Add a new order item
        // Using order_id=1 and item_id=2, both of which already exist
        // in your seed data (foreign keys must point to real rows).
        // ---------------------------------------------------------
        OrderItem newItem = new OrderItem(1, 2, 3, 180.0);   // orderId, itemId, quantity, price
        boolean added = orderItemDAO.addOrderItem(newItem);
        System.out.println("Add order item success? " + added);

        // ---------------------------------------------------------
        // TEST 4: Confirm it shows up when re-fetching by order_id
        // ---------------------------------------------------------
        List<OrderItem> updatedList = orderItemDAO.getOrderItemsByOrderId(1);
        System.out.println("Order items for order_id=1 after adding: " + updatedList.size());
        for (OrderItem oi : updatedList) {
            System.out.println(oi);
        }

        // ---------------------------------------------------------
        // TEST 5: Delete - commented out on purpose, uncomment to test
        // ---------------------------------------------------------
        // boolean deleted = orderItemDAO.deleteOrderItem(11);
        // System.out.println("Delete order item success? " + deleted);
    }
}