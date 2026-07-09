package com.foodapp.dao;

import com.foodapp.model.Restaurant;
import java.util.List;

/**
 * RestaurantDAO interface - defines WHAT operations are possible
 * on the restaurants table. RestaurantDAOImpl provides the actual JDBC code.
 */
public interface RestaurantDAO {

    boolean addRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(int restaurantId);

    List<Restaurant> getAllRestaurants();

    boolean updateRestaurant(Restaurant restaurant);

    boolean deleteRestaurant(int restaurantId);
}