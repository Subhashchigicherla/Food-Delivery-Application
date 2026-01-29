package com.tap.model;

import java.sql.Date;

public class Order 
{
	
		private int orderId;
		private int userId;
		private int restaurantId;
		private Date orderDate;
		private double totalAmount;
		private String status;
		private String paymentMode;
		
		public Order() {

		}

		public Order(int orderId, int userId, int restaurantId, Date orderDate, double totalAmount, String status,
				String paymentMode) {
			super();
			this.orderId = orderId;
			this.userId = userId;
			this.restaurantId = restaurantId;
			this.orderDate = orderDate;
			this.totalAmount = totalAmount;
			this.status = status;
			this.paymentMode = paymentMode;
		}

		public Order(int userId, int restaurantId, Date orderDate, double totalAmount, String status,
				String paymentMode) {
			this.userId = userId;
			this.restaurantId = restaurantId;
			this.orderDate = orderDate;
			this.totalAmount = totalAmount;
			this.status = status;
			this.paymentMode = paymentMode;// TODO Auto-generated constructor stub
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String isStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPaymentMode() {
			return paymentMode;
		}

		public void setPaymentMode(String payment) {
			this.paymentMode = payment;
		}


}
