<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.Cart" %>
<%@ page import="com.foodapp.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>My Cart - Food Delivery App</title>
</head>
<body>
    <div class="container">

    <h2>My Cart</h2>
    <p class="nav-links">
        <a href="restaurants">&laquo; Back to Restaurants</a>
    </p>

    <%
        Cart cart = (Cart) request.getAttribute("cart");
    %>

    <%
        if (cart.isEmpty()) {
    %>
        <p>Your cart is empty.</p>
    <%
        } else {
    %>

    <table border="1" cellpadding="8">
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th>Remove</th>
        </tr>

        <%
            for (CartItem item : cart.getItems()) {
        %>
        <tr>
            <td><%= item.getItemName() %></td>
            <td>&#8377;<%= item.getPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td>&#8377;<%= item.getSubtotal() %></td>
            <td><a href="cart?action=remove&itemId=<%= item.getItemId() %>">Remove</a></td>
        </tr>
        <%
            }
        %>
    </table>

    <h3>Total: &#8377;<%= cart.getTotal() %></h3>

    <form action="placeOrder" method="post">
        <button type="submit">Place Order</button>
    </form>

    <%
        }
    %>
    </div>

</body>
</html>
