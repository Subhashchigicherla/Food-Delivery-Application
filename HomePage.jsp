<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.tap.model.Restaurant" %>    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Restaurant Finder</title>
    <link rel="stylesheet" href="HomePage.css">
</head>
<body>
    <header class="hero">
        <div class="search-container">
            <input type="text" placeholder="Search restaurants..." class="search-input">
            <button class="search-button">Search</button>
        </div>
    </header>

    <main class="restaurant-grid">
        
        
        <%
        
        	List<Restaurant> restaurants=(List<Restaurant>)request.getAttribute("restaurants");
        	for(Restaurant  r: restaurants)
        	{
        
        %>
        
        <div class="restaurant-card">
        	<a href="menu?restaurantId=<%= r.getRestaurantId() %>" style="text-decoration:none"">
            <img src="<%= r.getImagePath() %>" class="food-image">
            <div class="restaurant-info">
                <h2><%= r.getName() %></h2>
                <p class="cuisine">🍕 <%= r.getCusinType() %></p>
                <div class="rating">★ <%= r.getRating() %></div>
                <p class="address">📍<%= r.getAddress() %></p>
                <p class="phone">📞 <%= r.getPhone() %></p>
            </div>
            </a>
        </div>
		
        <%
        	}        
        %>
        

        
    </main>

    <footer class="footer">
        <div class="footer-content">
            <div class="footer-section">
                <h3>Contact Us</h3>
                <p>Email: info@restaurantfinder.com</p>
                <p>Phone: 9347073231</p>
            </div>
            <div class="footer-section">
                <h3>Follow Us</h3>
                <p>Facebook | Instagram | Twitter</p>
            </div>
            <div class="footer-section">
                <h3>Address</h3>
                <p>1234 Restaurant Street</p>
                <p>Foodie City, FC 12345</p>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 Restaurant Finder. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>