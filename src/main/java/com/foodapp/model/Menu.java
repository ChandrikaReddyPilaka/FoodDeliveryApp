package com.foodapp.model;

/**
 * POJO class for the "menu" table.
 * Each menu item belongs to one restaurant (restaurantId links them).
 * No DB logic here - that goes in MenuDAO later.
 */
public class Menu {

    // ---- fields (match column names/types of menu table) ----
    private int itemId;          // menu.item_id
    private int restaurantId;    // menu.restaurant_id (foreign key to restaurants table)
    private String itemName;     // menu.item_name
    private String description;  // menu.description
    private double price;        // menu.price
    private boolean available;   // menu.available (true = can be ordered, false = hidden by admin)
    private String imageUrl;     // menu.image_url (NEW - path/URL to a photo of this dish)

    // ------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------

    public Menu() {
    }

    // Use this when INSERTING a new menu item WITHOUT an image yet
    public Menu(int restaurantId, String itemName, String description, double price, boolean available) {
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    // Use this when INSERTING a new menu item WITH an image
    public Menu(int restaurantId, String itemName, String description, double price,
                boolean available, String imageUrl) {
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.available = available;
        this.imageUrl = imageUrl;
    }

    // Use this when READING a menu row back from the DB (ResultSet)
    public Menu(int itemId, int restaurantId, String itemName, String description,
                double price, boolean available, String imageUrl) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.available = available;
        this.imageUrl = imageUrl;
    }

    // ------------------------------------------------------------
    // Getters and Setters
    // ------------------------------------------------------------

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "itemId=" + itemId +
                ", restaurantId=" + restaurantId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}