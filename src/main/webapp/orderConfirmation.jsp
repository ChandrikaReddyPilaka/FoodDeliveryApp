<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Order Confirmed - Food Delivery App</title>
</head>
<body>
    <div class="container">

    <div class="message-success">
        <svg viewBox="0 0 24 24" fill="none" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><path d="M8 12.5l2.5 2.5L16 9"/></svg>
        <h2>Order Placed Successfully!</h2>
        <p>Your order ID is: <strong>#<%= request.getAttribute("orderId") %></strong></p>
        <p>Status: PLACED</p>
    </div>

    <a href="restaurants">&laquo; Back to Restaurants</a>

    </div>
</body>
</html>
