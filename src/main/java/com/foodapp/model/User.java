package com.foodapp.model;

import java.sql.Timestamp;

/**
 * User - represents one row from the "users" table.
 * This is just a container to hold a user's data as it moves between
 * the database, the DAO, and the servlets/JSP pages.
 */
public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private Timestamp createdAt;

    // Empty constructor - needed so UserDAO can do "new User()" and fill it in afterward
    public User() {
    }

    // Full constructor - handy if you ever want to create a User in one line
    public User(String name, String email, String password, String phone, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    // ---- Getters and Setters ----
    // These are how other files (DAO, Servlets, JSP) read and write each piece of data

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}