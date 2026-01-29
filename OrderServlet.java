package com.tap.servlets;

import java.io.IOException;
import java.util.Map;

import com.tap.dao.OrdersDAO;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.daoimpl.OrdersDAOImpl;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;
import com.tap.utility.Cart;
import com.tap.utility.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet{
	
	
	private String paymentMode=null;
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();		
		Cart cart=(Cart)session.getAttribute("cart");
		
		User user=(User)session.getAttribute("user");
		String payment=req.getParameter("payment");
		if(payment.equals("credit")) {
			paymentMode=  payment;
		}else if(payment.equals("debit")){
			paymentMode=payment;
		}else if(payment.equals("UPI")) {
			paymentMode=payment;
		}else if(payment.equals("CashOnedelivery")) {
			paymentMode=payment;
		}
		Order order=new Order(user.getUserId(), cart.getRestaurantId(), null, cart.getTotalPrice(),"pending" , paymentMode);
		OrdersDAOImpl orderDao=new OrdersDAOImpl();
		int orderId=orderDao.addOrder(order);
		if(orderId>0) {
			 if (cart != null) {
		            Map<Integer, CartItem> items = cart.getItems();
		            if (!items.isEmpty()) {
		                for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
		                    CartItem cartItem = entry.getValue();
		                    int itemId=cartItem.getItemId();
		                    int quantity=cartItem.getQuantity();
		                    int totalPrice=cartItem.getPrice()*quantity;
		                    OrderItem orderItem=new OrderItem(orderId,itemId,quantity,totalPrice);
		                    OrderItemDAOImpl orderItemDAO=new OrderItemDAOImpl();
		                    orderItemDAO.addOrderItem(orderItem);
		                }
		            }}
		}
		
		session.setAttribute("orderId", orderId);
		req.getRequestDispatcher("OrderConfirmation.jsp").forward(req, resp);
	}
}