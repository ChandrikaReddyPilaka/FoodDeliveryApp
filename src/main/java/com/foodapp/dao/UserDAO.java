package com.foodapp.dao;
 
import com.foodapp.model.User;
import com.foodapp.util.DBConnection;   // adjust package if yours is different
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
/**
 * UserDAO - single class handling ALL database operations for the users table.
 * Servlets call these methods directly instead of writing SQL themselves.
 */
public class UserDAO {
 
    public boolean addUser(User user) {
 
        String sql = "INSERT INTO users (name, email, password, phone, role) VALUES (?, ?, ?, ?, ?)";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getRole());
 
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    public User getUserById(int userId) {
 
        String sql = "SELECT * FROM users WHERE user_id = ?";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, userId);
 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
    public List<User> getAllUsers() {
 
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
 
            while (rs.next()) {
                userList.add(mapRowToUser(rs));
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return userList;
    }
 
    public boolean updateUser(User user) {
 
        String sql = "UPDATE users SET name = ?, email = ?, phone = ? WHERE user_id = ?";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setInt(4, user.getUserId());
 
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    public boolean deleteUser(int userId) {
 
        String sql = "DELETE FROM users WHERE user_id = ?";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, userId);
 
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    public User login(String email, String password) {
 
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, email);
            ps.setString(2, password);
 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
    public boolean isEmailExists(String email) {
 
        String sql = "SELECT user_id FROM users WHERE email = ?";
 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, email);
 
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    /**
     * Private helper method - converts one ResultSet row into a User object.
     * Avoids repeating the same rs.getXxx() lines in getUserById(), getAllUsers(), and login().
     */
    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setRole(rs.getString("role"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        return user;
    }
}
 
