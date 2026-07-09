<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Home - Food Delivery App</title>
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= session.getAttribute("name") %>!</h2>

        <p class="nav-links">
            <a href="restaurants">Browse Restaurants</a>
            <a href="cart">View Cart</a>
            <a href="myOrders">My Orders</a>
            <a href="logout">Logout</a>
        </p>
    </div>
</body>
</html>
