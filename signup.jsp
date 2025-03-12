<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registration Page</title>
    <style>
      body {
        font-family: "Roboto", sans-serif;
        background-color: #feb47b;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
      }
      .form-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        width: 400px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      }
      h2 {
        text-align: center;
        margin-bottom: 20px;
      }
      .form-group {
        display: flex;
        flex-direction: column;
        margin-bottom: 10px;
      }
      label {
        font-size: 14px;
        color: #555;
        margin-bottom: 5px;
      }
      input[type="text"],
      input[type="email"],
      input[type="password"],
      input[type="tel"] {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
      }
      .gender-group {
        display: flex;
        justify-content: space-between;
        margin-bottom: 15px;
      }
      .register-btn {
        width: 100%;
        padding: 10px;
        background: linear-gradient(to right, #6a11cb, #2575fc);
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: 0.3s;
      }
      .register-btn:hover {
        opacity: 0.8;
      }
    </style>
  </head>
  <body>
    <div class="form-container">
      <h2>Registration</h2>
      <form action="signup" method="post" onsubmit="data()">
        <div class="form-group">
          <label for="fullname">Full Name</label>
          <input
            type="text"
            id="fullname"
            name="fullname"
            placeholder="Enter your name"
            required
          />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            placeholder="Enter your email"
            required
          />
        </div>
        <div class="form-group">
          <label for="phone">Phone Number</label>
          <input
            type="tel"
            id="phone"
            name="phone"
            placeholder="Enter your number"
            required
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Enter your password"
            required
          />
        </div>
        <div class="form-group">
          <label for="confirm-password">Confirm Password</label>
          <input
            type="password"
            id="confirm-password"
            name="confirm-password"
            placeholder="Confirm your password"
            required
          />
        </div>
        <button type="submit" class="register-btn">Register</button>
      </form>
    </div>
  </body>
  <script>
    function data() {
      var a = document.getElementById("password").value;
      var b = document.getElementById("confirm-password").value;
      var c = document.getElementById("phone").value;
      if (a != b) {
        alert("password didn't match!!!");
        return false;
      } else {
        true;
      }
      if(c.length != 10){
    	  alert("Enter valid phone number!!");
    	  return false;
      }
      else{
    	  true;
      }
    }
  </script>
</html>
