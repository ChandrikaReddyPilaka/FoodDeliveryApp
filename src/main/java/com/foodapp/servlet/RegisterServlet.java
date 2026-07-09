package com.foodapp.servlet;

import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handles the Signup form submission. URL pattern "/signup" must match the
 * form's action="signup" in signup.html.
 */
@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		User user = new User(name, email, password, phone, "CUSTOMER");
		UserDAO userDAO = new UserDAO();

		if (userDAO.isEmailExists(email)) {
			request.setAttribute("status", "error");
			request.setAttribute("message", "That email is already registered. Please try logging in instead.");
			request.getRequestDispatcher("signupResult.jsp").forward(request, response);
			return;
		}

		boolean success = userDAO.addUser(user);

		if (success) {
			request.setAttribute("status", "success");
			request.setAttribute("name", name);
			request.setAttribute("message", "Your account has been created successfully.");
		} else {
			request.setAttribute("status", "error");
			request.setAttribute("message", "Something went wrong. Please try again.");
		}

		request.getRequestDispatcher("signupResult.jsp").forward(request, response);
	}
}
