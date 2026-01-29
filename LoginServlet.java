package com.tap.servlets;



import java.io.IOException;
import java.io.PrintWriter;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;
import com.tap.utility.Cart;
import com.mysql.cj.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	static int count=3;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("Login attempt for email: " + email);
        HttpSession session=req.getSession();
        UserDAO userDAO = new UserDAOImpl();
        
        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            // User not found, redirect to signup page
            resp.sendRedirect("signup.jsp");
            return;
        }

        // Validate password (assuming hashed passwords are stored)
        if (password.equals(user.getPassword())) { // Replace this with a hash comparison
            resp.sendRedirect("home");
            session.setAttribute("user", user);
        } else {
        	// Invalid password, forward back to login with error
        	count--;
        	if (count<1) {
				//block user
        		req.getRequestDispatcher("block").forward(req, resp);
			} else {
				PrintWriter out=resp.getWriter();
	    		out.print("<h1 style='color:#FF0000;'> Wrong password "+count+" attemps left</h1>");
	            RequestDispatcher dispatcher = req.getRequestDispatcher("SignIn.jsp");
	            dispatcher.include(req, resp);
			}
          
        }
    }
}