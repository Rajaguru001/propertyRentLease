<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.propertyrent.model.SellerPropertyForm"%>
<%@ page import="com.propertyrent.model.*"%>
<%@ page import="com.propertyrent.dao.PropertyRentLeaseDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Approved Properties</title>
<style >
#properties-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    align-items: flex-start;
    margin-top: 20px;
}


.property {
    width: 1000px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
    margin-bottom: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.property h2 {
    margin-top: 0;
}

.property p {
    margin: 5px 0;
}

.property img {
    margin-bottom: 10px;
}

.navbar {
    background-color:#FF204E;
    overflow: hidden;
    margin-bottom: 20px;
}

.navbar a {
    display: inline-block; 
    color: white;
    text-align: center;
    padding: 14px 20px;
    text-decoration: none;
}

.navbar a:hover {
    background-color: #29252C;
}

.logo {
    display: flex;
    align-items: center;
    justify-content: center;
}

.logo img {
    max-height: 30px;
    margin-right: 10px;
}

button[type="submit"] {
    background-color: #FF204E;
    color: white;
    border: none;
    cursor: pointer;
    padding: 12px 24px;
    border-radius: 4px;
    transition: background-color 0.3s, box-shadow 0.3s;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

button[type="submit"]:hover {
    background-color: #FF1654;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.comment-box {
    margin-top: 20px;
    background-color: #f0f2f5;
    border-radius: 8px;
    padding: 20px;
}

.comment-box textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc; 
    border-radius: 4px;
    resize: none;
    font-family: Arial, sans-serif; 
    font-size: 14px; 
}

.comment-box textarea:focus {
    border-color: #FF204E; 
    outline: none;
    box-shadow: 0 0 5px rgba(255, 32, 78, 0.5); 
}
.property p {
    margin: 5px 0;
    font-family: 'Arial', sans-serif; 
    font-size: 16px;
    line-height: 1.5; 
    color: #333; 
    text-decoration: none; 
}
</style>
</head>
<body>
<form id="Propertybuyer" action="PropertyRentBuyer" method="post">
<div class="navbar">
    <h1 class="logo">
        <img src="image/EliteRentalslogo.jpg" alt="EliteRentals Logo">
        <span class="company-name">EliteRentals</span>
    </h1>
</div>
<h1>Approved Properties</h1>
<div id="properties-container">
    <%
        
        PropertyRentLeaseDAO propertyLeaseDAO = new PropertyRentLeaseDAO();
        List<SellerPropertyForm> approvedProperties = (List<SellerPropertyForm>) request.getAttribute("approvedProperties");
        if (approvedProperties != null && !approvedProperties.isEmpty()) {
            for (SellerPropertyForm property : approvedProperties) {
                List<PropertyImage> propertyImages = propertyLeaseDAO.getPropertyImages(property.getPropertyId());
                List<Comments> comments=propertyLeaseDAO.getcomment(property.getPropertyId());
    %>
    <div class="property">
        <h2>
            Property ID:
            <%= property.getPropertyId() %></h2>
            
            <p>
            Property Images:
            <% for (PropertyImage image : propertyImages) { %>
            <img
                src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(image.getImage()) %>"
                width="250" height="200" />
            <% } %>
        </p>
        <p>
            Type:
            <%= property.getPropertyType() %></p>
        <p>
            Square Feet:
            <%= property.getSqft() %></p>
        <p>
            Furnishing:<%= property.getFurnishing() %></p>
        <p>
            AvailableFrom:<%= property.getAvailableFrom() %></p>
        <p>
            Price:<%= property.getRent() %></p>
        <p>
            Address:<%= property.getAddress() %></p>
        <p>
            Posted On Date:<%= property.getPostedOnDate() %></p>
        <p>
            Location:<%= property.getLocation() %></p>
       
        <p>
            
            comments:</p>
             <%
            UsersInfo userId=(UsersInfo)session.getAttribute("user"); 
        %>
           <p> <% for(Comments comment : comments) { %>
            
            	
                <%= comment.getComment_section() %>
                
            <%} %>
</p>
       
        <input type="hidden" value=<%=userId.getId()%> name="id">
        <input type="hidden" value=<%= property.getPropertyId()%> name="propertyId"> 
         <input type="text" value=<%= userId.getEmail()%> name="buyerId">
          <input type="text" value=<%= property.getOwnerId()%> name="sellerId">
         
         <button type="submit">Book Now</button>
    </form>
    <form action="PropertyRentComment" method="post">
    <%
    
    %>
    
        <input type="hidden" value=<%=userId.getId()%> name="id">
        <input type="hidden" value=<%= property.getPropertyId()%> name="propertyId">    
        <input type="hidden" value=<%=userId.getUsername()%> name="username">
        <div class="comment-box">
            <textarea rows="4" cols="50" name="comment" placeholder="Enter your comment here..."></textarea>
            <button type="submit">Comment</button>
        </div>
    </form>
        
    </div>
    <%
            }
        } else {
    %>
    <p>No properties have been approved yet.</p>
    <%
        }
    %>
    
</div>
</body>
</html>
