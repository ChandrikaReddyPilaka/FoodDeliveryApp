package com.foodapp.dao.impl;

import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;
import com.foodapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderItemDAOImpl - the actual JDBC implementation of OrderItemDAO.
 * Every method signature here must match OrderItemDAO exactly.
 */
public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        String sql = "INSERT INTO order_items (order_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getItemId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getPrice());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        String sql = "SELECT * FROM order_items WHERE order_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToOrderItem(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> itemList = new ArrayList<>();
        // This is the important one: fetch every line item that belongs
        // to ONE order - exactly what an order confirmation page needs.
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    itemList.add(mapRowToOrderItem(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {

        String sql = "DELETE FROM order_items WHERE order_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Private helper method - converts one ResultSet row into an OrderItem object.
     */
    private OrderItem mapRowToOrderItem(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setOrderItemId(rs.getInt("order_item_id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setItemId(rs.getInt("item_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        return item;
    }
}