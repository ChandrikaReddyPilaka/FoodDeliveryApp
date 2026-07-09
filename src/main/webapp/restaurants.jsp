<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Restaurants - Food Delivery App</title>
</head>
<body>
    <div class="container">
    <h2>All Restaurants</h2>

    <%
        // Reading the data the servlet placed here with setAttribute().
        // This is a "scriptlet" - raw Java code embedded inside the JSP.
        // It's the simplest way to start with JSP, though larger projects
        // later move to JSTL tags to avoid mixing too much Java into HTML.
        List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
    %>

    <table border="1" cellpadding="8">
        <tr>
            <th>Photo</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Rating</th>
            <th>Menu</th>
        </tr>

        <%
            // Looping through the list using a normal Java for-each loop,
            // printing one <tr> per restaurant.
            for (Restaurant r : restaurantList) {
        %>
        <tr>
            <td>
                <!-- onerror: if the stored imageUrl is missing or broken,
                     swap in a small inline placeholder illustration
                     instead of showing a browser "broken image" icon. -->
                <img class="photo-cell"
                     src="<%= r.getImageUrl() %>"
                     alt="<%= r.getName() %>"
                     onerror="this.onerror=null;this.src='images/food-placeholder.svg'">
            </td>
            <td><%= r.getName() %></td>
            <td><%= r.getAddress() %></td>
            <td><%= r.getPhone() %></td>
            <td><%= r.getRating() %></td>
            <td><a href="menu?restaurantId=<%= r.getRestaurantId() %>">View Menu</a></td>
        </tr>
        <%
            }
        %>
    </table>
    </div>

</body>
</html>
