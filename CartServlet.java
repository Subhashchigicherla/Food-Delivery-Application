package com.tap.servlets;

import java.io.IOException;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;
import com.tap.utility.Cart;
import com.tap.utility.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session=request.getSession();
	Cart cart = (Cart)session.getAttribute("cart");
	
	int newRestaurantId= Integer.parseInt(request.getParameter("restaurantId"));
	Integer currentRestaurantId = (Integer)session.getAttribute("restaurantId");
	
	if(cart==null || newRestaurantId != currentRestaurantId)
	{
		cart=new Cart();
		session.setAttribute("cart", cart);
		session.setAttribute("restaurantId", newRestaurantId);
		cart.setRestaurantId(newRestaurantId);
	}
	
	String action = request.getParameter("action");
	
	if(action.equals("add"))
	{
		addItemToCart(request,cart);
	}
	else if(action.endsWith("update")){
		updateCartItem(request,cart);
	}
	else if(action.equals("remove")){
		removeItemFromCart(request,cart);
	}
	
	
System.out.println("action"+request.getParameter("action"));
System.out.println("itemId"+request.getParameter("itemId"));
System.out.println("quantity"+request.getParameter("quantity"));
System.out.println("restId"+request.getParameter("restaurantId"));
	

	response.sendRedirect("CartPage.jsp");
	
	}
	
	
	
	
	

	






	private void addItemToCart(HttpServletRequest request, Cart cart) {

		Integer itemId=Integer.parseInt(request.getParameter("itemId"));
		Integer quantity=Integer.parseInt(request.getParameter("quantity"));
		
		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);
		
		System.out.println("The menu in Cart servlet "+menuItem);
		
		if(menuItem!=null)
		{
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					menuItem.getPrice(),
					quantity
					);
			cart.addItem(item);
		}
	}






	private void updateCartItem(HttpServletRequest request, Cart cart) {

		int itemId=Integer.parseInt(request.getParameter("itemId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		cart.updateItem(itemId,quantity);
		
	}






	private void removeItemFromCart(HttpServletRequest request, Cart cart) {

		int itemId=Integer.parseInt(request.getParameter("itemId"));
		cart.removeItem(itemId);
		
		
		
		
	}
}
