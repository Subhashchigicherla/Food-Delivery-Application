package com.tap.servlets;

import java.io.IOException;
import java.sql.Date;
import com.tap.dao.OrdersDAO;
import com.tap.daoimpl.OrdersDAOImpl;
import com.tap.model.Order;
import com.tap.model.User;
import com.tap.utility.Cart;
import com.tap.utility.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet{
	
	private OrdersDAO orderDAO;
	
	 @Override
	public void init() throws ServletException {
		 
		 try {
			 
			 orderDAO = new OrdersDAOImpl();
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	 
	 
	 
	 
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 HttpSession session = request.getSession();
		 Cart cart = (Cart) session.getAttribute("cart");
		 User user=(User) session.getAttribute("loggedInUser");
		 
//		 System.out.println(user+"User is not null");
//		 System.out.println(cart+" cart is not null");
		 
		 if(cart != null && user != null && !cart.getItems().isEmpty()) {
			 
			 System.out.println("Inside checkout servlet IF is true");
			 
			 String paymentMethod=request.getParameter("paymentMethod");
			 
			 Order order = new Order();
			 order.setUserId(user.getUserId());
//			 System.out.println(session.getAttribute("restaurantId"));
//			 System.out.println(Integer.parseInt((String)session.getAttribute("restaurantId")));
			 
			 order.setRestaurantId((int)session.getAttribute("restaurantId"));
			 order.setOrderDate(new Date(0));
			 order.setStatus("Pending");
			 order.setPaymentMode(paymentMethod);
			 
			 
			 int totalAmount=0;
			 for(CartItem item : cart.getItems().values()) {
				 
//				 order.addOrdersItem(item);throwing an error
				 totalAmount+=item.getPrice() * item.getQuantity();
				 
			 }
			 order.setTotalAmount(totalAmount);
			 
			 orderDAO.addOrder(order);
			 
			 session.removeAttribute("cart");
			 session.setAttribute("order", order);
			 System.out.println("Checkout");
			 response.sendRedirect("OrderConfirmation.jsp");
			 
		 }else {
			 
			 response.sendRedirect("HomePage.jsp");
		 }
		 
		 
	 
	 
	 }
	

}
