<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="SignUp.css">
</head>
<body>
    <div class="container">
        <div class="signup-box">
            <h2>Create Account</h2>
            <form>
                <div class="input-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" required placeholder="Enter your name">
                </div>
                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" required placeholder="Enter your email">
                </div>
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" required placeholder="Enter your password">
                </div>
                <div class="input-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" required placeholder="Enter your phone number">
                </div>
                <button type="submit">Sign Up</button>
                <p class="login-link">Already have an account? <a href="SignIn.jsp">Login here</a></p>
            </form>
        </div>
    </div>
</body>
</html>