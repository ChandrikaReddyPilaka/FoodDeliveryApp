package com.foodapp.dao;

import com.foodapp.dao.impl.OrderDAOImpl;
import com.foodapp.model.Order;

import java.util.List;

/**
 * TestOrderDAO - a plain class with a main() method to manually test
 * OrderDAOImpl. Right-click this file -> Run As -> Java Application.
 */
public class TestOrderDAO {

    public static void main(String[] args) {

        OrderDAO orderDAO = new OrderDAOImpl();

        // ---------------------------------------------------------
        // TEST 1: Get all orders (you have 10 from your seed script)
        // ---------------------------------------------------------
        List<Order> allOrders = orderDAO.getAllOrders();
        System.out.println("Total orders in DB: " + allOrders.size());
        for (Order o : allOrders) {
            System.out.println(o);
        }

        // ---------------------------------------------------------
        // TEST 2: Get orders for ONE user (user_id = 1)
        // This is what a real "My Orders" page uses.
        // ---------------------------------------------------------
        List<Order> userOrders = orderDAO.getOrdersByUserId(1);
        System.out.println("Orders for user_id=1: " + userOrders.size());
        for (Order o : userOrders) {
            System.out.println(o);
        }

        // ---------------------------------------------------------
        // TEST 3: Add a new order
        // Notice: after addOrder() runs, newOrder.getOrderId() will be
        // automatically filled in with the new auto-generated ID -
        // that's the RETURN_GENERATED_KEYS behavior in action.
        // ---------------------------------------------------------
        Order newOrder = new Order(1, 1, 250.0, "PLACED", "/images/test_order.jpg");
        boolean added = orderDAO.addOrder(newOrder);
        System.out.println("Add order success? " + added);
        System.out.println("New order's generated ID: " + newOrder.getOrderId());

        // ---------------------------------------------------------
        // TEST 4: Update order status
        // ---------------------------------------------------------
        if (added) {
            boolean statusUpdated = orderDAO.updateOrderStatus(newOrder.getOrderId(), "DELIVERED");
            System.out.println("Update order status success? " + statusUpdated);
        }

        // ---------------------------------------------------------
        // TEST 5: Delete - commented out on purpose, uncomment to test
        // ---------------------------------------------------------
        // boolean deleted = orderDAO.deleteOrder(newOrder.getOrderId());
        // System.out.println("Delete order success? " + deleted);
    }
}