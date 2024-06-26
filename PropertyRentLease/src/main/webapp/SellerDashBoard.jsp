<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.propertyrent.model.*"%>
<%@ page import="com.propertyrent.dao.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.Base64"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Seller Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }
    
    header {
        background-color: #FF204E;
        color: #fff;
        padding: 10px;
        text-align: center;
    }
    
    nav {
        background-color: #FF204E;
        padding: 10px;
        text-align: center;
    }
    
    nav a {
        color: #fff;
        text-decoration: none;
        padding: 10px;
    }
    
    nav a:hover {
        background-color: #555;
    }
    
    .container {
        width: 80%;
        margin: auto;
        padding: 20px;
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    
    .content {
        padding: 20px;
    }
    
    footer {
        background-color: #333;
        color: #fff;
        text-align: center;
        padding: 10px;
        position: fixed;
        width: 100%;
        bottom: 0;
    }
</style>
</head>
<body>
    <header>
        <h1>Seller Dashboard</h1>
    </header>
    
    <nav>
        <a href="ContentPage.jsp">Home</a>
        <a href="#">Logout</a>
        <a href="PostProperty.jsp">post property</a>
    </nav>
    
    <div class="container">
        <div class="content">
        			<%
            UsersInfo userId=(UsersInfo)session.getAttribute("user"); 
        %>
       
             <% 
             
        List<SellerDashBoardRequest> sellerdashboard = PropertyRentLeaseDAO.sellerdashboard(userId.getId());
        if (sellerdashboard != null && !sellerdashboard.isEmpty()) {
            for (SellerDashBoardRequest requests : sellerdashboard) {
        %>
            <p>Owner ID: <%= requests.getOwner_id() %></p>
            <p>Property ID: <%= requests.getProperty_id() %></p>
            <p>Rent ID: <%= requests.getRent_id() %></p>
             <form action="PropertyRentSellerDashBoard" method="post">
  <input type="hidden" name="propertyId" value="<%= requests.getProperty_id() %>">
  <input type="hidden" name="rentid" value="<%=requests.getRent_id() %>">
                                <button type="submit">Approval</button>
 </form>
           
            
            <hr>
        <% 
            }
        } else {
        %>
            <p>No data available</p>
        <% } %>

<h2>Welcome back, <%= ((UsersInfo) session.getAttribute("user")).getUsername() %>!</h2>
<p>This is your Seller Dashboard.</p>



            
        </div>
    </div>
    
    <footer>
        &copy; 2024 PropertyRent. All rights reserved.
    </footer>
</body>
</html>
