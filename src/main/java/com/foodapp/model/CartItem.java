package com.foodapp.model;

/**
 * CartItem - represents ONE item sitting in a user's cart, before checkout.
 *
 * NOTE: this is NOT a database table. It only exists in memory, inside
 * the HttpSession, while the user is browsing/shopping. It becomes a
 * real OrderItem (saved to the database) only when the user places
 * the order. This is why it doesn't need a DAO or SQL - it's a
 * temporary, session-only object.
 */
public class CartItem {

    private int itemId;        // references menu.item_id
    private String itemName;   // stored here too, so the cart page doesn't
                                // need to re-query the menu table just to
                                // display the name
    private double price;      // price per unit at the time it was added
    private int quantity;

    public CartItem() {
    }

    public CartItem(int itemId, String itemName, double price, int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Handy helper - the subtotal for this single line (price x quantity).
    // Putting this here means every page that shows the cart doesn't need
    // to repeat this multiplication itself.
    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}