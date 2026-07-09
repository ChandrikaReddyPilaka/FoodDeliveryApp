package com.foodapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Cart - holds ALL the CartItems for one user's shopping session.
 * One Cart object will be stored in HttpSession per logged-in user.
 */
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    // Tracks which restaurant this cart's items belong to. Your orders
    // table links one order to one restaurant, so a cart in this app
    // is scoped to a single restaurant at a time (like most real
    // food delivery apps - you can't checkout items from two
    // restaurants in one order).
    private int restaurantId;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    // Adds an item to the cart. If the same item is added again
    // (e.g. clicking "Add to Cart" twice on the same dish), we
    // increase the quantity instead of creating a duplicate row -
    // this is what a real shopping cart does.
    public void addItem(CartItem newItem) {
        for (CartItem existing : items) {
            if (existing.getItemId() == newItem.getItemId()) {
                existing.setQuantity(existing.getQuantity() + newItem.getQuantity());
                return;   // found and updated - stop here, don't add a duplicate
            }
        }
        items.add(newItem);   // not found - this is a genuinely new item
    }

    public void removeItem(int itemId) {
        items.removeIf(item -> item.getItemId() == itemId);
        // removeIf() with a lambda: removes any item matching the condition.
        // Cleaner than manually looping with an Iterator to avoid
        // ConcurrentModificationException.
    }

    public void clear() {
        items.clear();   // used after an order is successfully placed
    }

    // Total cost of everything in the cart - sums each item's subtotal.
    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}