package com.tap.daoimpl;

import com.tap.dao.MenuDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.model.Menu;
import com.tap.utility.DBConnection;

	public class MenuDAOImpl implements MenuDAO
	{
		
		private static final String INSERT_MENU_QUERY="INSERT INTO `menu`(`itemName`, `description`, `price`, `ratings`, `isAvailable`, `imagePath`) values(?,?,?,?,?,?)";
		
		private static final String GET_MENU_QUERY="SELECT * FROM `menu` where `menuId`=?";
		
		private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `itemName`=?, `description`=?, `price`=?, `ratings`=?, `isAvailable`=?, `imagePath`=? WHERE `menuId`=?";

		private static final String DELETE_MENU_QUERY="DELETE FROM `menu` WHERE `menuId`=?";
		
		private static final String GET_ALL_MENUS="SELECT * FROM `menu`";
		
		private static final String GET_MENU_BYRESTAURANT="SELECT * FROM `menu` WHERE `restaurantId`=?" ;
		
		@Override
		public void addMenu(Menu menu)
		{
			
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(INSERT_MENU_QUERY);)
			{
				
				
				prepareStatement.setString(1, menu.getItemName());
				prepareStatement.setString(2, menu.getDescription());
				prepareStatement.setInt(3, menu.getPrice());
				prepareStatement.setFloat(4, menu.getRatings());
				prepareStatement.setBoolean(5, menu.isAvailable());
				prepareStatement.setString(6, menu.getImagePath());
				
				int res=prepareStatement.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		@Override
		public Menu getMenu(int menuId)
		{
			Menu menu=null;
			
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_MENU_QUERY);)
			{
				
				prepareStatement.setInt(1, menuId);
				ResultSet res=prepareStatement.executeQuery();
				while(res.next()) {
					menu=extractMenu(res);
				}
				
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return menu;
		}
		
		
		
		
		
		

		@Override
		public void updateMenu(Menu menu) 
		{
			
		Connection connection=null;
		PreparedStatement prepareStatement;
		
		try {
			connection=DBConnection.getConnection();
			prepareStatement=connection.prepareStatement(UPDATE_MENU_QUERY);
			
			prepareStatement.setString(1, menu.getItemName());
			prepareStatement.setString(2, menu.getDescription());
			prepareStatement.setInt(3, menu.getPrice());
			prepareStatement.setFloat(4, menu.getRatings());
			prepareStatement.setBoolean(5, menu.isAvailable());
			prepareStatement.setString(6, menu.getImagePath());
			prepareStatement.setInt(7, menu.getMenuId());
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		}
		
		
		
		
		
		
		
		
		
		

		@Override
		public void deleteMenu(int menuId) 
		{

			Connection connection=DBConnection.getConnection();
			
			try {
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_MENU_QUERY);
			
				prepareStatement.setInt(1, menuId);
				prepareStatement.executeUpdate();
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		
		
		

		@Override
		public List<Menu> getAllMenus() 
		{
			ArrayList<Menu> menuList=new ArrayList<Menu>();
			
			try {
				Connection connection=DBConnection.getConnection();
				Statement statement=connection.createStatement();
				ResultSet res=statement.executeQuery(GET_ALL_MENUS);
				
				while(res.next())
				{
					Menu menu=extractMenu(res);
					menuList.add(menu);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return menuList;
		}
		
		
		
		
		@Override
		public List<Menu> getAllMenusByRestaurant(int restaurantId) {

			ArrayList<Menu> menuList=new ArrayList<Menu>();
			try (Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_MENU_BYRESTAURANT);){
				
				prepareStatement.setInt(1, restaurantId);
				ResultSet resultSet=prepareStatement.executeQuery();
				while(resultSet.next()) {
					Menu menu=extractMenu(resultSet);
					menuList.add(menu);
					
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			
			}
			return menuList;
		}
		
		
		
		
		
		
		
		
		
		Menu extractMenu(ResultSet res) throws SQLException
		{
			int menuId=res.getInt("menuId");
			int restaurantId=res.getInt("restaurantId");
			String itemName=res.getString("itemName");
			String description=res.getString("description");
			int price=res.getInt("price");
			float ratings=res.getFloat("ratings");
			boolean isAvailable=res.getBoolean("isAvailable");
			String imagePath=res.getString("imagePath");
			Menu menu=new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
			return menu;
		}














		




	

}
