<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.tap.model.Menu"  %>
    
    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Restaurant Menu</title>
    <link rel="stylesheet" href="MenuPage.css">
  </head>
  <body>
    <div class="hero">
      <div class="container">
        <h1>Our Delicious Menu</h1>
        <div class="menu-grid">
        
        
        <%
        	List<Menu> menuList=(List<Menu>)request.getAttribute("menus");
        
        	if(menuList != null && !menuList.isEmpty())
        	{
        
        	for( Menu menuItem: menuList )
        	{
        %>
        
        
          <div class="menu-item">
            <div class="food-image">
              <img src="<%=menuItem.getImagePath() %>" alt="Classic Burger">
            </div>
            <h2><%= menuItem.getItemName() %></h2>
            <p class="description"><%= menuItem.getDescription() %></p>
            <div class="price">Price : ₹ <%= menuItem.getPrice() %></div>
            <div class="rating">★ <%= menuItem.getRatings() %></div>
            
            
            <form action="cart" method="post">
            	<input type="hidden" name="restaurantId" value="<%= menuItem.getRestaurantId()%>">
            	<input type="hidden" name="itemId" value="<%= menuItem.getMenuId() %>">
            	<input type="hidden" name="quantity" value="1" min="1">
            	<input type="hidden" name="action" value="add">
            	
            <button type ="submit"class="add-to-cart">Add to Cart</button>
            
            </form>
          </div>
          <%
        	}
        	}else{
        	
          %>
          <p style="text-align: center; color: #757575">No menu items available.please check back</p>
          <%
        	}
          
          %>
        </div>
        
      </div>
    </div>
  </body>
</html>