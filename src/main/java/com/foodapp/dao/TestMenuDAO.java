package com.foodapp.dao;

import com.foodapp.dao.impl.MenuDAOImpl;
import com.foodapp.model.Menu;

import java.util.List;

/**
 * TestMenuDAO - a plain class with a main() method to manually test
 * MenuDAOImpl. Right-click this file -> Run As -> Java Application.
 */
public class TestMenuDAO {

    public static void main(String[] args) {

        MenuDAO menuDAO = new MenuDAOImpl();

        // ---------------------------------------------------------
        // TEST 1: Get all menu items (you have 10 from your seed script)
        // ---------------------------------------------------------
        List<Menu> allItems = menuDAO.getAllMenuItems();
        System.out.println("Total menu items in DB: " + allItems.size());
        for (Menu m : allItems) {
            System.out.println(m);
        }

        // ---------------------------------------------------------
        // TEST 2: Get menu items for ONE restaurant (restaurant_id = 1)
        // This is the important one - a real menu page uses this,
        // not getAllMenuItems().
        // ---------------------------------------------------------
        List<Menu> restaurantMenu = menuDAO.getMenuByRestaurantId(1);
        System.out.println("Menu items for restaurant_id=1: " + restaurantMenu.size());
        for (Menu m : restaurantMenu) {
            System.out.println(m);
        }

        // ---------------------------------------------------------
        // TEST 3: Get menu item by ID
        // ---------------------------------------------------------
        Menu fetched = menuDAO.getMenuItemById(1);
        System.out.println("Menu item with id=1: " + fetched);

        // ---------------------------------------------------------
        // TEST 4: Add a new menu item
        // ---------------------------------------------------------
        Menu newItem = new Menu(1, "Test Item", "A test menu item", 99.0, true);
        boolean added = menuDAO.addMenuItem(newItem);
        System.out.println("Add menu item success? " + added);

        // ---------------------------------------------------------
        // TEST 5: Update a menu item
        // ---------------------------------------------------------
        if (fetched != null) {
            fetched.setPrice(75.0);
            boolean updated = menuDAO.updateMenuItem(fetched);
            System.out.println("Update menu item success? " + updated);
        }

        // ---------------------------------------------------------
        // TEST 6: Delete - commented out on purpose, uncomment to test
        // ---------------------------------------------------------
        // boolean deleted = menuDAO.deleteMenuItem(11);
        // System.out.println("Delete menu item success? " + deleted);
    }
}
