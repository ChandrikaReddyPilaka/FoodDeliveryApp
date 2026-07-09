package com.foodapp.dao.impl;

import com.foodapp.dao.RatingDAO;
import com.foodapp.model.Rating;
import com.foodapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * RatingDAOImpl - the actual JDBC implementation of RatingDAO.
 * Every method signature here must match RatingDAO exactly.
 */
public class RatingDAOImpl implements RatingDAO {

    @Override
    public boolean addRating(Rating rating) {

        String sql = "INSERT INTO ratings (user_id, restaurant_id, rating, comment) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rating.getUserId());
            ps.setInt(2, rating.getRestaurantId());
            ps.setInt(3, rating.getRating());
            ps.setString(4, rating.getComment());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rating getRatingById(int ratingId) {

        String sql = "SELECT * FROM ratings WHERE rating_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ratingId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToRating(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Rating> getAllRatings() {

        List<Rating> ratingList = new ArrayList<>();
        String sql = "SELECT * FROM ratings";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ratingList.add(mapRowToRating(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingList;
    }

    @Override
    public List<Rating> getRatingsByRestaurantId(int restaurantId) {

        List<Rating> ratingList = new ArrayList<>();
        String sql = "SELECT * FROM ratings WHERE restaurant_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ratingList.add(mapRowToRating(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingList;
    }

    @Override
    public boolean deleteRating(int ratingId) {

        String sql = "DELETE FROM ratings WHERE rating_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ratingId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Private helper method - converts one ResultSet row into a Rating object.
     */
    private Rating mapRowToRating(ResultSet rs) throws SQLException {
        Rating rating = new Rating();
        rating.setRatingId(rs.getInt("rating_id"));
        rating.setUserId(rs.getInt("user_id"));
        rating.setRestaurantId(rs.getInt("restaurant_id"));
        rating.setRating(rs.getInt("rating"));
        rating.setComment(rs.getString("comment"));
        return rating;
    }
}