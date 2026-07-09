package com.foodapp.dao.impl;

import com.foodapp.dao.LocationDAO;
import com.foodapp.model.Location;
import com.foodapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * LocationDAOImpl - the actual JDBC implementation of LocationDAO.
 * Every method signature here must match LocationDAO exactly.
 */
public class LocationDAOImpl implements LocationDAO {

    @Override
    public boolean addLocation(Location location) {

        String sql = "INSERT INTO locations (user_id, label, address_line, city, pincode, is_default) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, location.getUserId());
            ps.setString(2, location.getLabel());
            ps.setString(3, location.getAddressLine());
            ps.setString(4, location.getCity());
            ps.setString(5, location.getPincode());
            ps.setBoolean(6, location.isDefault());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Location getLocationById(int locationId) {

        String sql = "SELECT * FROM locations WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, locationId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToLocation(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Location> getLocationsByUserId(int userId) {

        List<Location> locationList = new ArrayList<>();
        String sql = "SELECT * FROM locations WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    locationList.add(mapRowToLocation(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationList;
    }

    @Override
    public boolean updateLocation(Location location) {

        String sql = "UPDATE locations SET label = ?, address_line = ?, city = ?, pincode = ?, is_default = ? WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, location.getLabel());
            ps.setString(2, location.getAddressLine());
            ps.setString(3, location.getCity());
            ps.setString(4, location.getPincode());
            ps.setBoolean(5, location.isDefault());
            ps.setInt(6, location.getLocationId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteLocation(int locationId) {

        String sql = "DELETE FROM locations WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, locationId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Private helper method - converts one ResultSet row into a Location object.
     */
    private Location mapRowToLocation(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt("location_id"));
        location.setUserId(rs.getInt("user_id"));
        location.setLabel(rs.getString("label"));
        location.setAddressLine(rs.getString("address_line"));
        location.setCity(rs.getString("city"));
        location.setPincode(rs.getString("pincode"));
        location.setDefault(rs.getBoolean("is_default"));
        return location;
    }
}


