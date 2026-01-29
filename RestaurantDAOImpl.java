package com.tap.daoimpl;

import com.tap.dao.RestaurantDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

	public class RestaurantDAOImpl implements RestaurantDAO
	{
		private static final String INSERT_USER_QUERY="INSERT into `restaurant`(`name`,`address`,`phone`,`rating`,`cusinType`,`isActive`,`eta`,`imagePath`) values(?,?,?,?,?,?,?,?)";
		
		private static final String GET_RESTAURANT_QUERY="SELECT * FROM `restaurant` where restaurantId=?";
		
		private static final String UPDATE_RESTAURANT_QUERY="UPDATE `restaurant` SET `name`=? `address`=? `phone`=? `rating`=? `cusinType`=?  `isActive`=? `eta`=? `imagePath`=? where `restaurantId'=?";
		
		private static final String DELETE_RESTAURANT_QUERY="DELETE * FROM `restaurant` where restaurantId=?";
		
		private static final String GET_ALL_RESTAURANTS="SELECT * FROM `restaurant`";

		
		@Override
		public void addRestaurant(Restaurant restaurant) 
		{
			
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(INSERT_USER_QUERY);)
					
			{
				
				prepareStatement.setString(1, restaurant.getName());
				prepareStatement.setString(2,restaurant.getAddress());
				prepareStatement.setString(3,restaurant.getPhone());
				prepareStatement.setFloat(4, restaurant.getRating());
				prepareStatement.setString(5, restaurant.getCusinType());
				prepareStatement.setBoolean(6, restaurant.isActive());	
				prepareStatement.setString(7, restaurant.getEta());
				prepareStatement.setString(8, restaurant.getImagePath());
				
				int res=prepareStatement.executeUpdate();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}

		
		
		
		
		
		
		
		@Override
		public Restaurant getRestaurant(int restaurantId) 
		{
			
			Restaurant restaurant=null;
			
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_RESTAURANT_QUERY);)
			{
				
				prepareStatement.setInt(1, restaurantId);
				ResultSet res=prepareStatement.executeQuery();
				restaurant=extractRestaurant(res);
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return restaurant;
			
		}

		









		@Override
		public void updateRestaurant(Restaurant restaurant) 
		{
			
			Connection connection=null;
			PreparedStatement prepareStatement;
			try {
				connection=DBConnection.getConnection();
				prepareStatement=connection.prepareStatement(UPDATE_RESTAURANT_QUERY);
				
				prepareStatement.setString(1, restaurant.getName());
				prepareStatement.setString(2, restaurant.getAddress());
				prepareStatement.setString(3, restaurant.getPhone());
				prepareStatement.setFloat(4,  restaurant.getRating());
				prepareStatement.setString(5, restaurant.getCusinType());
				prepareStatement.setBoolean(6, restaurant.isActive());
				prepareStatement.setString(7, restaurant.getEta());
				prepareStatement.setString(8, restaurant.getImagePath());
				prepareStatement.setInt(9, restaurant.getRestaurantId());
				
				prepareStatement.executeUpdate();
			
			
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}

		
		
		

		
		
		@Override
		public void deleteRestaurant(int restaurantId) {
			
			
			try {
				Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_RESTAURANT_QUERY);
				prepareStatement.setInt(1, restaurantId);
				prepareStatement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			
			}
			
		}
		


		
		
		
		
		
		

		@Override
		public List<Restaurant> getAllRestaurants() 
		{


			ArrayList<Restaurant> restaurantList=new  ArrayList<Restaurant>();
			
			try {
				
				Connection connection=DBConnection.getConnection();
				Statement statement=connection.createStatement();
				ResultSet res=statement.executeQuery(GET_ALL_RESTAURANTS);
				
				while(res.next())
				{
					
					Restaurant restaurant=extractRestaurant(res);
					restaurantList.add(restaurant);
				}
			
			
			
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			return restaurantList;
		}
		
		
		
		Restaurant extractRestaurant(ResultSet res) throws SQLException
		{
			int restaurantId=res.getInt("restaurantId");
			String name=res.getString("name");
			String address=res.getString("address");
			String phone=res.getString("phone");
			float rating=res.getFloat("rating");
			String cusinType=res.getString("cusinType");
			boolean isActive=res.getBoolean("isActive");
			String eta=res.getString("eta");
			int adminUserId=res.getInt("adminUserId");
			String imagePath=res.getString("imagePath");
			
			Restaurant restaurant=new Restaurant(restaurantId, name, address, phone, rating, cusinType, isActive, eta, adminUserId, imagePath);
		
			return restaurant;
		}

}
