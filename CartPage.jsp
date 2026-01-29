<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.tap.utility.Cart,com.tap.utility.CartItem" %>

<%@ page import="com.mysql.cj.x.protobuf.MysqlxCrud.Collection" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cart</title>
    <link rel="stylesheet" href="Cart.css" />
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
  </head>
  <body>
   <nav class="navbar">
    <div class="logo">Food Nation</div>
    <div class="nav-icons">
      
      <a href="profile.jsp" class="profile-icon"><i class="fa-regular fa-user"></i></a>
       <%if((session.getAttribute("user"))==null){ %>
      <a href="login.jsp" class="login-icon">Login</a>
      <%}else{ %>
    <form action="logoutServlet" method="post" style="display: inline;">
    <input type="hidden" name="page" value="cart">
  <button type="submit" class="logout-link">Logout</button>
</form>

      <%} %>
    </div>
  </nav>
    <header class="cart-header">
      <h1>Your Cart</h1>
    </header>

    <div class="cart-container">
      <!-- Cart Item Section -->
      <div class="cart-items">
      <%
    System.out.println("Session cart: " + session.getAttribute("cart"));
%>
        
         <%-- Retrieve the cart from the session --%>
            <%
            Cart cart = (Cart) session.getAttribute("cart");
            Integer restaurantId =(Integer) session.getAttribute("restaurantId");
            if (cart != null) {
                Map<Integer, CartItem> items = cart.getItems();
                if (!items.isEmpty()) {
                    for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
                        CartItem cartItem = entry.getValue();
            %>
        <!-- Example Cart Item -->
        <div class="cart-item">
        <div class="item-details">
          <h3><%= cartItem.getItemName() %></h3>
          <p>Price: ₹<span class="item-price" name><%= cartItem.getPrice() %></span></p>
          </div>
          <div class="cart-item-actions">
          <div class="quantity-controls">
			<div  >
				   <form action="cart" method="post">
				    <input type="hidden" name="restaurantId" value="<%= cartItem.getRestaurantId() %>" />
			        <input type="hidden" name="itemId" value="<%= cartItem.getItemId() %>" />
			        <input type="hidden" name="action" value="update" />
			        <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() - 1 %>" />
			        <button class="quantity-btn decrease"  <% if (cartItem.getQuantity() == 1) { %>disabled<% } %>>-</button>
			    	</form>
			 </div>
			<p class="quantity"><%= cartItem.getQuantity() %></p>
			<div >
			    <form action="cart" method="post">
			    	<input type="hidden" name="restaurantId" value="<%= cartItem.getRestaurantId() %>" />
			        <input type="hidden" name="itemId" value="<%= cartItem.getItemId() %>" />
			        <input type="hidden" name="action" value="update" />
			        <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() + 1 %>" />
			        <button class="quantity-btn increase">+</button>
			    </form>
			</div>

          </div>
          <div>
          <form action="cart" method="post" class="remove-form">
         <input type="hidden" name="restaurantId" value="<%= cartItem.getRestaurantId() %>" />
        <input type="hidden" name="itemId" value="<%= cartItem.getItemId() %>" />
        <input type="hidden" name="action" value="remove"/>
        <button class="remove" type="submit"><i class="fa-regular fa-trash-can"></i></button>
    </form>
    </div>
    </div>
   </div>
        <%
                    }
                } else {
                	out.println("<p>Your cart is empty.</p>");
                }
            } else {
                out.println("<p>Your cart is empty.</p>");
            }
			%>
        <!-- Add more items here -->
      </div>

      <!-- Buttons Section -->
      <div class="cart-navigation">
      <%--  <%int restId=Integer.parseInt(request.getParameter("restaurantId")); %> --%>
      <a href="menu?restaurantId=<%=session.getAttribute("restaurantId") %>">
      <button class="menu-btn">Add More</button>
        </a>
      </div>

      <!-- Total and Checkout -->
      <div class="cart-total">
        <h3>Total: ₹<span class="total-amount"><% if(session.getAttribute("cart")!=null){%><%= cart.getTotalPrice() %><%}else{ %>0.0<%} %></span></h3>
        <%if(session.getAttribute("user")!=null) {%>
        <a href="CheckOut.jsp">
        <%if (session.getAttribute("cart")!=null) {%>
        <button class="checkout-btn">Proceed to Checkout</button>
        </a>
        <%} %>
        <% } else {%> <a href="CheckOut.jsp">
        <button class="checkout-btn">Proceed to Checkout</button>
        <%} %>
      </div>
    </div>

    <footer>
      <p>&copy; 2025 Your Store. All rights reserved.</p>
    </footer>

    <!-- <script src="/FoodNation/scripts/cart.js" defer></script> -->
  </body>
</html>