package com.foodapp.dao;

import com.foodapp.dao.impl.RestaurantDAOImpl;
import com.foodapp.model.Restaurant;

import java.util.List;

/**
 * TestRestaurantDAO - a plain class with a main() method to manually test
 * RestaurantDAOImpl without needing a browser, servlet, or Tomcat.
 * Right-click this file -> Run As -> Java Application to execute it.
 */
public class TestRestaurantDAO {

    public static void main(String[] args) {

        // Programming to the interface: declared type is RestaurantDAO,
        // actual object is RestaurantDAOImpl.
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

        // ---------------------------------------------------------
        // TEST 1: Get all restaurants (you already have 10 from your
        // original SQL script, so this should print all of them)
        // ---------------------------------------------------------
        List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
        System.out.println("Total restaurants in DB: " + allRestaurants.size());
        for (Restaurant r : allRestaurants) {
            System.out.println(r);
        }

        // ---------------------------------------------------------
        // TEST 2: Get restaurant by ID
        // ---------------------------------------------------------
        Restaurant fetched = restaurantDAO.getRestaurantById(1);
        System.out.println("Restaurant with id=1: " + fetched);

        // ---------------------------------------------------------
        // TEST 3: Add a new restaurant
        // ---------------------------------------------------------
        Restaurant newRestaurant = new Restaurant(
                "Test Restaurant",
                "123 Test Street",
                "9000000000",
                4.0,
                "/images/test_restaurant.jpg"
        );
        boolean added = restaurantDAO.addRestaurant(newRestaurant);
        System.out.println("Add restaurant success? " + added);

        // ---------------------------------------------------------
        // TEST 4: Update a restaurant
        // Replace 1 with a real restaurant_id from your database
        // ---------------------------------------------------------
        if (fetched != null) {
            fetched.setRating(4.9);
            boolean updated = restaurantDAO.updateRestaurant(fetched);
            System.out.println("Update restaurant success? " + updated);
        }

        // ---------------------------------------------------------
        // TEST 5: Delete a restaurant
        // Commented out on purpose - only uncomment when you actually
        // want to test deletion (avoid wiping real data accidentally)
        // ---------------------------------------------------------
        // boolean deleted = restaurantDAO.deleteRestaurant(11);
        // System.out.println("Delete restaurant success? " + deleted);
    }
}