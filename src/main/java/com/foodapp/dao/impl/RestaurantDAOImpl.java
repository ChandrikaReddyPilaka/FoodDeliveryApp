package com.foodapp.dao.impl;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;
import com.foodapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * RestaurantDAOImpl - the actual JDBC implementation of RestaurantDAO.
 * Every method signature here must match RestaurantDAO exactly.
 */
public class RestaurantDAOImpl implements RestaurantDAO {

    @Override
    public boolean addRestaurant(Restaurant restaurant) {

        String sql = "INSERT INTO restaurants (name, address, phone, rating, image_url) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getPhone());
            ps.setDouble(4, restaurant.getRating());
            ps.setString(5, restaurant.getImageUrl());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {

        String sql = "SELECT * FROM restaurants WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToRestaurant(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurantList = new ArrayList<>();
        String sql = "SELECT * FROM restaurants";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                restaurantList.add(mapRowToRestaurant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {

        String sql = "UPDATE restaurants SET name = ?, address = ?, phone = ?, rating = ?, image_url = ? WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getPhone());
            ps.setDouble(4, restaurant.getRating());
            ps.setString(5, restaurant.getImageUrl());
            ps.setInt(6, restaurant.getRestaurantId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRestaurant(int restaurantId) {

        String sql = "DELETE FROM restaurants WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Private helper method - converts one ResultSet row into a Restaurant object.
     * Used by getRestaurantById() and getAllRestaurants() to avoid repeating code.
     */
    private Restaurant mapRowToRestaurant(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setName(rs.getString("name"));
        restaurant.setAddress(rs.getString("address"));
        restaurant.setPhone(rs.getString("phone"));
        restaurant.setRating(rs.getDouble("rating"));
        restaurant.setImageUrl(rs.getString("image_url"));
        restaurant.setCreatedAt(rs.getTimestamp("created_at"));
        return restaurant;
    }
}