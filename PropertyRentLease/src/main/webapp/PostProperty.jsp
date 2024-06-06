<%@page import="com.propertyrent.model.UsersInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.propertyrent.model.SellerPropertyForm" %>
<%@ page import="com.propertyrent.model.UsersInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 
<form id="propertyForm" action="PropertyRentSellerForm" method="post" enctype="multipart/form-data"> 
  <table>
    <label for="property_type">Property Type:</label>
    <input type="text" id="property_type" name="property_type" required><br>
    
    <label for="sqft">Sqft:</label>
    <input type="number" id="sqft" name="sqft" required><br>
    
    <label for="furnishing">Furnishing:</label>
    <input type="text" id="furnishing" name="furnishing" required><br>
    
    <label for="available_from">Available From:</label>
    <input type="date" id="available_from" name="available_from" required><br>
    
    <label for="rent_price">Rent Price:</label>
    <input type="number" id="rent_price" name="rent_price" required><br>
    
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>
    
    <label for="posted_on_date">Posted on Date:</label>
    <input type="date" id="posted_on_date" name="posted_on_date" required><br>
  
    <label for="EB_Bill">EB Bill:</label>
    <input type="file" id="EB_Bill" name="EB_Bill" accept="image/*" required><br>
    
    <label for="property_images">Property Images:</label>
    <input type="file" id="property_images" name="property_images" accept="image/*" multiple required><br>
    <% 
    UsersInfo userId=(UsersInfo)session.getAttribute("user"); 
    
    %>
    <input type="number" value=<%=userId.getId()%> name="id">
    
    <button type="submit">Submit</button>
</form>
</table>
</form>

<script>
document.getElementById("propertyForm").addEventListener("submit", function(event){
    event.preventDefault(); 

 
    alert("Thanks for submission!");
    
     this.submit();
});
</script>

</body>
</html>
