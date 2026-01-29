<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Checkout Page</title>
  <link rel="stylesheet" href="CheckOut.css">
 
</head>
<body>
  <div class="checkout-card">
    <div class="header">
      <h1>Checkout</h1>
      <p>Complete your purchase</p>
    </div>
<form action="order" method="post">
    <div class="section">
      <label class="section-title" for="address">Shipping Address</label>
      <textarea id="address" placeholder="Enter your complete shipping address..."></textarea>
    </div>

    <div class="section">
      <h2 class="section-title">Payment Method</h2>
      <div class="payment-options">
      
        <label class="payment-option">
          <input type="radio" name="payment" value="credit">
          <span>Credit Card</span>
        </label>
        
        <label class="payment-option">
          <input type="radio" name="payment" value="debit">
          <span>Debit Card</span>
        </label>
        
        <label class="payment-option">
          <input type="radio" name="payment" value="UPI">
          <span>UPI</span>
        </label>
        
        <label class="payment-option">
          <input type="radio" name="payment" value="CashOnedelivery">
          <span>Cash On Delivery</span>
        </label>
        
        
      </div>
    </div>

	
    <button type="submit" class="place-order-btn">Place Order</button>
   
</form>
  </div>
      
</body>
</html>