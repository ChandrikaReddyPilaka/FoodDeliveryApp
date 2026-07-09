<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Order" %>
<%@ page import="com.foodapp.model.OrderItem" %>
<%@ page import="com.foodapp.dao.OrderItemDAO" %>
<%@ page import="com.foodapp.dao.impl.OrderItemDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>My Orders - Food Delivery App</title>
</head>
<body>
    <div class="container">

    <h2>My Orders</h2>
    <p class="nav-links plain">
        <a href="restaurants">&laquo; Back to Restaurants</a>
    </p>

    <%
        List<Order> orderList = (List<Order>) request.getAttribute("orderList");

        // We need OrderItemDAO here too, since each order's line items
        // aren't part of the Order object itself - they live in a
        // separate table (order_items), so we fetch them per order below.
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    %>

    <%
        if (orderList.isEmpty()) {
    %>
        <div class="empty-state">
            <svg viewBox="0 0 24 24" fill="none" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><path d="M14 2v6h6"/><path d="M9 13h6"/><path d="M9 17h6"/></svg>
            <p>You haven't placed any orders yet.</p>
        </div>
    <%
        } else {
            for (Order order : orderList) {
    %>

    <div class="order-card">
        <h3>Order #<%= order.getOrderId() %> - <%= order.getStatus() %></h3>
        <p>Placed on: <%= order.getOrderDate() %></p>
        <p>Total: &#8377;<%= order.getTotalAmount() %></p>

        <%
            // Fetch and list the items that belong to THIS specific order.
            List<OrderItem> items = orderItemDAO.getOrderItemsByOrderId(order.getOrderId());
        %>

        <table border="1" cellpadding="6">
            <tr>
                <th>Item ID</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <%
                for (OrderItem item : items) {
            %>
            <tr>
                <td><%= item.getItemId() %></td>
                <td><%= item.getQuantity() %></td>
                <td>&#8377;<%= item.getPrice() %></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <%
            }
        }
    %>
    </div>

</body>
</html>
