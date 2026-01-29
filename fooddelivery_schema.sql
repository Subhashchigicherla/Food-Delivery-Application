-- Create Database
CREATE DATABASE IF NOT EXISTS fooddelivery;
USE fooddelivery;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS users;

-- Create Users Table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'customer',
    created_date DATE DEFAULT CURDATE(),
    last_login_date DATE,
    UNIQUE KEY unique_username (username),
    UNIQUE KEY unique_email (email)
);

-- Create Restaurant Table
CREATE TABLE restaurant (
    restaurant_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    rating FLOAT DEFAULT 0.0,
    cusine_type VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    eta VARCHAR(50),
    admin_user_id INT,
    image_path VARCHAR(255),
    FOREIGN KEY (admin_user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-- Create Menu Table
CREATE TABLE menu (
    menu_id INT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id INT NOT NULL,
    item_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price INT NOT NULL,
    ratings FLOAT DEFAULT 0.0,
    is_available BOOLEAN DEFAULT TRUE,
    image_path VARCHAR(255),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE CASCADE,
    INDEX idx_restaurant (restaurant_id)
);

-- Create Orders Table
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    order_date DATE NOT NULL,
    total_amount DOUBLE NOT NULL,
    status VARCHAR(50) DEFAULT 'pending',
    payment_mode VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE CASCADE,
    INDEX idx_user (user_id),
    INDEX idx_restaurant (restaurant_id),
    INDEX idx_status (status)
);

-- Create Order Items Table
CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    menu_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id) ON DELETE CASCADE,
    INDEX idx_order (order_id)
);

-- Insert Sample Data

-- Insert Users
INSERT INTO users (name, username, password, email, phone, address, role) VALUES
('John Doe', 'johndoe', 'password123', 'john@example.com', '9876543210', '123 Main St, City', 'customer'),
('Jane Smith', 'janesmith', 'password456', 'jane@example.com', '9876543211', '456 Oak Ave, City', 'customer'),
('Admin User', 'admin', 'admin123', 'admin@example.com', '9876543212', '789 Admin Rd, City', 'admin');

-- Insert Restaurants
INSERT INTO restaurant (name, address, phone, rating, cusine_type, is_active, eta, admin_user_id, image_path) VALUES
('Pizza Palace', '100 Pizza Lane', '9123456789', 4.5, 'Italian', TRUE, '30 mins', 3, '/images/pizza_palace.jpg'),
('Burger Barn', '200 Burger Blvd', '9123456790', 4.2, 'American', TRUE, '25 mins', 3, '/images/burger_barn.jpg'),
('Sushi Supreme', '300 Sushi Street', '9123456791', 4.8, 'Japanese', TRUE, '40 mins', 3, '/images/sushi_supreme.jpg');

-- Insert Menu Items
INSERT INTO menu (restaurant_id, item_name, description, price, ratings, is_available, image_path) VALUES
(1, 'Margherita Pizza', 'Classic pizza with fresh mozzarella', 300, 4.5, TRUE, '/images/margherita.jpg'),
(1, 'Pepperoni Pizza', 'Pizza with pepperoni slices', 350, 4.6, TRUE, '/images/pepperoni.jpg'),
(1, 'Veggie Pizza', 'Pizza with fresh vegetables', 280, 4.3, TRUE, '/images/veggie.jpg'),
(2, 'Classic Burger', 'Beef burger with lettuce and tomato', 250, 4.4, TRUE, '/images/classic_burger.jpg'),
(2, 'Cheese Burger', 'Burger with melted cheddar cheese', 280, 4.5, TRUE, '/images/cheese_burger.jpg'),
(2, 'Chicken Burger', 'Grilled chicken burger', 220, 4.2, TRUE, '/images/chicken_burger.jpg'),
(3, 'California Roll', 'Crab, cucumber, and avocado', 400, 4.8, TRUE, '/images/california_roll.jpg'),
(3, 'Spicy Tuna Roll', 'Spicy tuna with wasabi', 420, 4.7, TRUE, '/images/spicy_tuna.jpg'),
(3, 'Salmon Nigiri', 'Fresh salmon nigiri', 450, 4.9, TRUE, '/images/salmon_nigiri.jpg');

-- Insert Orders
INSERT INTO orders (user_id, restaurant_id, order_date, total_amount, status, payment_mode) VALUES
(1, 1, CURDATE(), 650, 'completed', 'credit_card'),
(2, 2, CURDATE(), 530, 'pending', 'debit_card'),
(1, 3, CURDATE(), 820, 'completed', 'upi');

-- Insert Order Items
INSERT INTO order_items (order_id, menu_id, quantity, total_price) VALUES
(1, 1, 2, 600),
(1, 2, 1, 350),
(2, 4, 2, 500),
(2, 6, 1, 220),
(3, 7, 1, 400),
(3, 9, 1, 420);
