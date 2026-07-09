package com.foodapp.dao;

import com.foodapp.model.Menu;
import java.util.List;

/**
 * MenuDAO interface - defines WHAT operations are possible on the menu table.
 * MenuDAOImpl provides the actual JDBC code.
 */
public interface MenuDAO {

    boolean addMenuItem(Menu menu);

    Menu getMenuItemById(int itemId);

    List<Menu> getAllMenuItems();

    // Special method: a menu page always shows items for ONE restaurant,
    // not every menu item in the whole database.
    List<Menu> getMenuByRestaurantId(int restaurantId);

    boolean updateMenuItem(Menu menu);

    boolean deleteMenuItem(int itemId);
}