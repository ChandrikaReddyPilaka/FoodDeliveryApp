package com.foodapp.dao.impl;

import com.foodapp.dao.OrderDAO;
import com.foodapp.model.Order;
import com.foodapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderDAOImpl - the actual JDBC implementation of OrderDAO.
 * Every method signature here must match OrderDAO exactly.
 */
public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean addOrder(Order order) {

        String sql = "INSERT INTO orders (user_id, restaurant_id, total_amount, status, image_url) VALUES (?, ?, ?, ?, ?)";

        // Statement.RETURN_GENERATED_KEYS lets us read back the auto-generated
        // order_id right after insert - useful because OrderItems need to know
        // WHICH order they belong to (we'll use this in OrderItemDAO next).
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getImageUrl());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderId(generatedKeys.getInt(1));   // fill in the new order_id on the object
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Order getOrderById(int orderId) {

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToOrder(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orderList.add(mapRowToOrder(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        // ORDER BY order_date DESC -> most recent order shows first, like a real order history page

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orderList.add(mapRowToOrder(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        // Only status typically changes after an order is placed
        // (PLACED -> DELIVERED / CANCELLED) - so a focused method here
        // is more realistic than a generic "update everything" method.

        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrder(int orderId) {

        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Private helper method - converts one ResultSet row into an Order object.
     */
    private Order mapRowToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setRestaurantId(rs.getInt("restaurant_id"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        order.setStatus(rs.getString("status"));
        order.setImageUrl(rs.getString("image_url"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        return order;
    }
}