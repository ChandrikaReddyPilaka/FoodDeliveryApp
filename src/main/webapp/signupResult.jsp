<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Signup Result - Food Delivery App</title>
</head>
<body>
    <div class="container">

    <%
        String status = (String) request.getAttribute("status");   // "success" or "error"
        String message = (String) request.getAttribute("message");
    %>

    <% if ("success".equals(status)) { %>
        <div class="message-success">
            <h2>Welcome, <%= request.getAttribute("name") %>!</h2>
            <p><%= message %></p>
        </div>
        <a href="login.html">Go to Login</a>
    <% } else { %>
        <div class="message-error">
            <h2>Signup Failed</h2>
            <p><%= message %></p>
        </div>
        <a href="signup.html">&laquo; Try Again</a>
    <% } %>

    </div>
</body>
</html>
