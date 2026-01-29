package com.tap.daoimpl;

import com.tap.dao.OrderItemDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.model.OrderItem;
import com.tap.model.User;
import com.tap.utility.DBConnection;

	public class OrderItemDAOImpl implements OrderItemDAO
	{
		
		private static final String INSERT_ORDERITEM_QUERY="INSERT INTO `orderitem` (`quantity`, `totalPrice`) values(?,?)";
		
		private static final String  GET_ORDERITEM_QUERY="SELECT * FROM `orderitem` WHERE `orderItemId`=?";
		
		private static final String UPDATE_ORDERITEM_QUERY="UPDATE `oderitem` SET `quantity`=? `totalPrice`=? WHERE orderItemId=?";
		
		private static final String DELETE_ORDERITEM_QUERY="DELETE FROM `orderitem` where `orderItemId`=?";
		
		private static final String GET_ALL_ORDERITEM_QUERY="SELECT * FROM `orderitem`";

		@Override
		public void addOrderItem(OrderItem orderItem) 
		{
			
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(INSERT_ORDERITEM_QUERY);	)
			{
				
				
				prepareStatement.setInt(1, orderItem.getQuantity());
				prepareStatement.setInt(2, orderItem.getQuantity());
				
				int res =prepareStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		
		
		
		
		
		
		@Override
		public OrderItem getOrderItem(int orderItemId) 
		{
			
			OrderItem orderItem=null;
			
			
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_ORDERITEM_QUERY);	)
			{
				
				prepareStatement.setInt(1, orderItemId);
				
				
				ResultSet res=prepareStatement.executeQuery();
				
				 orderItem=extractOrderItem(res);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return orderItem;
		}
		
		
		
		
		
		
		
		
		

		@Override
		public void updateOrderItem(OrderItem orderItemId) 
		{

			Connection connection=null;
			PreparedStatement prepareStatement;
			
			try {
				
				connection=DBConnection.getConnection();
				 prepareStatement=connection.prepareStatement(UPDATE_ORDERITEM_QUERY);
				
				prepareStatement.setInt(1, orderItemId.getQuantity());
				prepareStatement.setInt(2, orderItemId.getTotalPrice());
				
				prepareStatement.setInt(3, orderItemId.getOrderId());
				
				prepareStatement.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		
		
		
		
		@Override
		public void deleteOrderItem(int orderItemId)
		{

			
			try {
				
				Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_ORDERITEM_QUERY);
				
				prepareStatement.setInt(1, orderItemId);
				prepareStatement.executeUpdate();


				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		
		
		
		
		
		
		

		@Override
		public List<OrderItem> getAllOrderItems()
		{
			OrderItem orderItem=null;
			
			ArrayList<OrderItem> orderItemsList= new ArrayList<OrderItem>();
			
			
			try {
				
				Connection connection=DBConnection.getConnection();
				Statement statement=connection.createStatement();
				ResultSet res=statement.executeQuery(GET_ALL_ORDERITEM_QUERY);
				
				while(res.next())
				{

					orderItem=extractOrderItem(res);
					orderItemsList.add(orderItem);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return orderItemsList;
		}
		
		
		
		
		
		
		
		
		
		OrderItem extractOrderItem(ResultSet res) throws SQLException
		{
			int orderItemId=res.getInt("orderItemId");
			int orderId=res.getInt("orderId");
			int menuId=res.getInt("menuId");
			int quantity=res.getInt("quantity");
			int totalPrice=res.getInt("totalPrice");
			
			OrderItem orderItem=new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
			
			return orderItem;
		}




}
