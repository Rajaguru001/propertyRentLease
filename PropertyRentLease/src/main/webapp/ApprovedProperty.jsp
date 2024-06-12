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
</head>
<body>
<form id="Propertybuyer" action="PropertyRentBuyer" method="post">
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
					width="100" height="100" />
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
			    comments:
			  <% for(Comments comment : comments) { %>
			  
			  
			    <%= comment.getComment_section() %>
			     
			   	<%} %>

			<% 
    UsersInfo userId=(UsersInfo)session.getAttribute("user"); 
    %>
    <input type="text" value=<%=userId.getId()%> name="id">
    <input type="text" value=<%= property.getPropertyId()%> name="propertyId"> 
    
    <button type="submit">Book Now</button>
    	</form>
    <form action="PropertyRentComment" method="post">
    <%
   
    %>
    
     	  <input type="text" value=<%=userId.getId()%> name="id">
    <input type="text" value=<%= property.getPropertyId()%> name="propertyId"> 	
      <input type="text" value=<%=userId.getUsername()%> name="username">
    <textarea rows="4" cols="50" name="comment">
Enter text here...</textarea>
<button type="submit">comment</button>
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
