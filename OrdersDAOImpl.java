package com.tap.daoimpl;

import com.tap.dao.OrdersDAO;


	import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import com.tap.dao.OrdersDAO;
	import com.tap.model.Order;
	import com.tap.utility.DBConnection;

	public class OrdersDAOImpl implements OrdersDAO
	{
		private static final String INSERT_ORDER_QUERY="INSERT INTO `orders` (`userId`,`restaurantId`,`totalAmount`, `status`, `paymentMode`)values(?,?,?,?,?)";

		private static final String GET_ORDER_QUERY="SELECT * FROM `orders` WHERE `orderId`=?";
		
		private static final String UPDATE_ORDER_QUERY="UPDATE `orders` SET `totalAmount`=?, `status`=?, `paymentMode`=? WHERE `orderId`=?";
		
		private static final String DELETE_ORDER_QUERY="DELETE FROM `orders` where `orderId`=?";
		
		private static final String GET_ALL_OREDER_QUERY="SELECT * FROM `orders`";
		
		@Override
		public int addOrder(Order order) 
		{
			int orderId=0;
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(INSERT_ORDER_QUERY,Statement.RETURN_GENERATED_KEYS);)
			{
				
				
				prepareStatement.setInt(1, order.getUserId());
				prepareStatement.setInt(2, order.getRestaurantId());
				prepareStatement.setDouble(3, order.getTotalAmount());
				prepareStatement.setString(4, order.isStatus());
				prepareStatement.setString(5, order.getPaymentMode());
				 
				int res=prepareStatement.executeUpdate();
				
				ResultSet res1=prepareStatement.getGeneratedKeys();
				while(res1.next()) {
					orderId=res1.getInt(1);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return orderId;
		}
		
		
		
		
		
		
		
		@Override
		public Order getOrder(int orderId)
		{
			Order order=null;
		
			Connection connection=DBConnection.getConnection();
			
			try {
				PreparedStatement prepareStatement=connection.prepareStatement(GET_ORDER_QUERY);
				prepareStatement.setInt(1, orderId);
				
				ResultSet res=prepareStatement.executeQuery();
				order=extractOrder(res);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return order;
		}

		
		
		
		
		
		@Override
		public void updateOrder(Order order) 
		{
		
			Connection connection=null;
			PreparedStatement prepareStatement;
			
			try {
				connection=DBConnection.getConnection();
				prepareStatement=connection.prepareStatement(UPDATE_ORDER_QUERY);
				
				prepareStatement.setDouble(1, order.getTotalAmount());
				prepareStatement.setString(2, order.isStatus());
				prepareStatement.setString(3, order.getPaymentMode());
				
				prepareStatement.executeUpdate();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		

		@Override
		public void deleteOrder(int orderId) 
		{

			Connection connection=DBConnection.getConnection();
			
			
			try {
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_ORDER_QUERY);
				
				prepareStatement.setInt(1, orderId);
				prepareStatement.executeUpdate();
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		

		@Override
		public List<Order> getAllOrders() 
		{

			ArrayList<Order> ordersList=new ArrayList<Order>();
			
			Connection connection=DBConnection.getConnection();
			
			try {
				Statement statement=connection.createStatement();
				
				ResultSet res=statement.executeQuery(GET_ALL_OREDER_QUERY);
				
				while(res.next())
				{
					
					Order order=extractOrder(res);
					
					ordersList.add(order);
				}
				
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return ordersList;
		}
		
		
		
		
		
		Order extractOrder(ResultSet res) throws SQLException
		{
			int orderId=res.getInt("orderId");
			int userId=res.getInt("userId");
			int restaurantId=res.getInt("restaurantId");
			Date orderDate=res.getDate("orderDate");
			double toatalAmount=res.getDouble("totalAmount");
			String status=res.getString("status");
			String paymentMode=res.getString("paymentMode");
			
			Order order=new Order(orderId, userId, restaurantId, orderDate, toatalAmount, status, paymentMode);
			
			return order;
		}
	

	

}
