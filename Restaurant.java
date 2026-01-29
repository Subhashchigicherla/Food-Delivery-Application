package com.tap.model;

public class Restaurant 
{
	
		private int restaurantId;
		private String name;
		private String address;
		private String phone;
		private float rating;
		private String cusinType;
		private boolean isActive;
		private String eta;
		private int adminUserId;
		private String imagePath;
		
		public Restaurant() {
		
		}

		public Restaurant(int restaurantId, String name, String address, String phone, float rating, String cusinType,
				boolean isActive, String eta, int adminUserId, String imagePath) {
			super();
			this.restaurantId = restaurantId;
			this.name = name;
			this.address = address;
			this.phone = phone;
			this.rating = rating;
			this.cusinType = cusinType;
			this.isActive = isActive;
			this.eta = eta;
			this.adminUserId = adminUserId;
			this.imagePath = imagePath;
		}

		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public float getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}

		public String getCusinType() {
			return cusinType;
		}

		public void setCusinType(String cusinType) {
			this.cusinType = cusinType;
		}

		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public String getEta() {
			return eta;
		}

		public void setEta(String eta) {
			this.eta = eta;
		}

		public int getAdminUserId() {
			return adminUserId;
		}

		public void setAdminUserId(int adminUserId) {
			this.adminUserId = adminUserId;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}


}
