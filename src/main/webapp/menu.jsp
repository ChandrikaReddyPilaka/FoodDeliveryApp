<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Menu - Food Delivery App</title>
</head>
<body>
    <div class="container">

    <h2>Menu</h2>
    <p class="nav-links plain">
        <a href="restaurants">&laquo; Back to Restaurants</a>
        <a href="cart">View Cart</a>
    </p>

    <%
        List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
    %>

    <table border="1" cellpadding="8">
        <tr>
            <th>Photo</th>
            <th>Item Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Available</th>
            <th>Add to Cart</th>
        </tr>

        <%
            for (Menu m : menuList) {
        %>
        <tr>
            <td>
                <!-- Falls back to a simple inline plate/fork illustration
                     if the item's imageUrl is empty or fails to load. -->
                <img class="photo-cell"
                     src="<%= m.getImageUrl() %>"
                     alt="<%= m.getItemName() %>"
                     onerror="this.onerror=null;this.src='images/food-placeholder.svg'">
            </td>
            <td><%= m.getItemName() %></td>
            <td><%= m.getDescription() %></td>
            <td>&#8377;<%= m.getPrice() %></td>
            <td><%= m.isAvailable() ? "Yes" : "No" %></td>
            <td>
                <!-- Each item gets its OWN small form, since each row needs
                     to submit its own itemId/name/price independently. -->
                <form action="cart" method="post">
                    <input type="hidden" name="itemId" value="<%= m.getItemId() %>">
                    <input type="hidden" name="itemName" value="<%= m.getItemName() %>">
                    <input type="hidden" name="price" value="<%= m.getPrice() %>">
                    <input type="hidden" name="restaurantId" value="<%= m.getRestaurantId() %>">
                    <input type="number" name="quantity" value="1" min="1" style="width: 50px;">
                    <button type="submit">Add</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    </div>

</body>
</html>
