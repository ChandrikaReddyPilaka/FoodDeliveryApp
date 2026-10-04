 Food Delivery Application

A simple Food Delivery Web Application developed as a B.Tech 2nd-year project. The application allows users to register, log in, browse restaurants and menus, add food items to a cart, and place food orders.

 Project Overview

The Food Delivery Application is a web-based system designed to provide a simple online food ordering experience.

Users can:

- Create a new account
- Log in securely
- View available restaurants
- View restaurant menus
- View food item images and prices
- Add food items to the cart
- View cart items
- Place orders
- View order confirmation
- View previous orders

The project demonstrates how a Java web application can interact with a MySQL database using JDBC and Servlets.


 Technologies Used

Frontend

- HTML
- CSS
- JavaScript
- JSP

Backend

- Java 17
- Java Servlets
- JDBC

Database

- MySQL 8.0

Server

- Apache Tomcat 10

Development Tools

- Eclipse IDE
- Maven
- MySQL

 Features

 User Registration

New users can create an account by providing their required details.

 User Login

Registered users can log in using their credentials.

 Restaurant Listing

Users can view available restaurants along with restaurant images.

 Menu Display

Users can select a restaurant and view its available food items, images, and prices.

 Shopping Cart

Users can add food items to their cart and review the selected items before placing an order.

 Order Placement

Users can place an order from the items available in their cart.

 Order Confirmation

After placing an order, users receive an order confirmation page.

 My Orders

Users can view their previously placed orders.


 Project Structure

FoodDeliveryApp/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── foodapp/
│   │   │           ├── dao/
│   │   │           ├── model/
│   │   │           ├── servlet/
│   │   │           └── util/
│   │   │
│   │   └── webapp/
│   │       ├── css/
│   │       ├── images/
│   │       ├── cart.jsp
│   │       ├── home.jsp
│   │       ├── login.html
│   │       ├── login.jsp
│   │       ├── menu.jsp
│   │       ├── myorders.jsp
│   │       ├── orderconfirmation.jsp
│   │       ├── restaurant.jsp
│   │       ├── signup.html
│   │       └── signup.jsp
│   │
├── pom.xml
└── README.md

«The exact package and folder names may vary depending on the project structure.»



 Database

The application uses MySQL as its database.

The database stores information related to:

- Users
- Restaurants
- Menu items
- Orders
- Order items
- Ratings
- Other supporting information

The Java application communicates with MySQL using JDBC.


 Application Workflow

             Start
               │
               ▼
          Login / Signup
               │
               ▼
        Restaurant Listing
               │
               ▼
          Select Restaurant
               │
               ▼
          View Menu Items
               │
               ▼
          Add Items to Cart
               │
               ▼
           View Cart
               │
               ▼
          Place Order
               │
               ▼
       Order Confirmation
               │
               ▼
           My Orders


 Login Flow

1. User opens the application.
2. Existing users enter their login credentials.
3. New users can create an account using the signup option.
4. After successful login, the user is redirected to the home page.
5. The user can browse restaurants and order food.

 Food Ordering Flow

1. Select a restaurant.
2. View the restaurant's menu.
3. Select a food item.
4. Add the item to the cart.
5. Open the cart.
6. Review the selected items.
7. Place the order.
8. The order and order items are stored in the database.
9. The order confirmation page is displayed.
10. The user can view the order later through My Orders.


How to Run the Project

Step 1: Install Required Software

Install the following:

- Java JDK 17
- Eclipse IDE
- Apache Tomcat 10
- MySQL
- MySQL Workbench
- Maven

Step 2: Clone the Repository

git clone YOUR_GITHUB_REPOSITORY_URL

Step 3: Open the Project in Eclipse

1. Open Eclipse.
2. Select the project workspace.
3. Import the Food Delivery project.
4. Make sure Maven dependencies are downloaded.
5. Check that Java 17 is configured.

Step 4: Configure MySQL

1. Open MySQL Workbench.
2. Create the required database.
3. Create the project tables.
4. Insert the required sample data.
5. Update the database connection details in the project.

Example:

String URL = "jdbc:mysql://localhost:3306/foodapp";
String USERNAME = "root";
String PASSWORD = "your_password";

Replace the username, password, and database name with your own details.

Step 5: Configure Apache Tomcat

1. Open Eclipse.
2. Go to Window → Preferences.
3. Select Server → Runtime Environments.
4. Add Apache Tomcat 10.
5. Select your Tomcat installation folder.
6. Apply the settings.

Step 6: Run the Project

1. Right-click the project.
2. Select Run As → Run on Server.
3. Select Apache Tomcat.
4. Click Finish.
5. Tomcat will start the application.

Open the application in your browser using the URL generated by Eclipse/Tomcat.


 Testing

The following functionalities can be tested:

Test| Expected Result
User Registration| New user account is created
User Login| User successfully logs in
Restaurant Display| Restaurants are displayed
Menu Display| Menu items are displayed
Add to Cart| Selected item is added to cart
View Cart| Cart displays selected items
Place Order| Order is stored in database
Order Confirmation| Confirmation page is displayed
My Orders| Previous orders are displayed

 Security

The project uses basic session management for maintaining logged-in users.

For a production-level application, additional security features such as password hashing, input validation, HTTPS, and advanced authentication should be implemented.

 Future Enhancements

The application can be improved by adding:

- Online payment integration
- Order tracking
- Restaurant search
- Food category filtering
- User profile management
- Restaurant owner dashboard
- Admin dashboard
- Delivery partner module
- Order status updates
- Email/SMS notifications
- Improved security
- Responsive mobile design

 Learning Outcomes

Through this project, the following concepts were practiced:

- Java programming
- Object-Oriented Programming
- Java Servlets
- JSP
- JDBC
- MySQL database connectivity
- DAO architecture
- CRUD operations
- HTTP request and response handling
- Session management
- HTML and CSS
- Maven project management
- Apache Tomcat deployment
- Git and GitHub

 Project Type

Academic Project

Course: B.Tech – Artificial Intelligence & Machine Learning

Project: Food Delivery Web Application

 License

This project was developed for educational and academic purposes.

 Acknowledgement

This project was developed as part of academic learning to understand the development of a Java-based web application using Java, JSP, Servlets, JDBC, MySQL, and Apache Tomcat.

If you find this project useful, consider giving the repository a  on GitHub.
