package com.foodapp.dao.impl;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;
import com.foodapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuDAOImpl - the actual JDBC implementation of MenuDAO.
 * Every method signature here must match MenuDAO exactly.
 */
public class MenuDAOImpl implements MenuDAO {

    @Override
    public boolean addMenuItem(Menu menu) {

        String sql = "INSERT INTO menu (restaurant_id, item_name, description, price, available, image_url) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getItemName());
            ps.setString(3, menu.getDescription());
            ps.setDouble(4, menu.getPrice());
            ps.setBoolean(5, menu.isAvailable());
            ps.setString(6, menu.getImageUrl());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Menu getMenuItemById(int itemId) {

        String sql = "SELECT * FROM menu WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMenu(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Menu> getAllMenuItems() {

        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                menuList.add(mapRowToMenu(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }

    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {

        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    menuList.add(mapRowToMenu(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }

    @Override
    public boolean updateMenuItem(Menu menu) {

        String sql = "UPDATE menu SET item_name = ?, description = ?, price = ?, available = ?, image_url = ? WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, menu.getItemName());
            ps.setString(2, menu.getDescription());
            ps.setDouble(3, menu.getPrice());
            ps.setBoolean(4, menu.isAvailable());
            ps.setString(5, menu.getImageUrl());
            ps.setInt(6, menu.getItemId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMenuItem(int itemId) {

        String sql = "DELETE FROM menu WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Private helper method - converts one ResultSet row into a Menu object.
     */
    private Menu mapRowToMenu(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setItemId(rs.getInt("item_id"));
        menu.setRestaurantId(rs.getInt("restaurant_id"));
        menu.setItemName(rs.getString("item_name"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));
        menu.setAvailable(rs.getBoolean("available"));
        menu.setImageUrl(rs.getString("image_url"));
        return menu;
    }
}