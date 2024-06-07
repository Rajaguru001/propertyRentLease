<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.propertyrent.model.SellerPropertyForm"%>
<%@ page import="com.propertyrent.model.*"%>
<%@ page import="com.propertyrent.dao.PropertyRentLeaseDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.Base64"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="dashboard.css">
<style>

</style>
</head>
<body>
	<div class="sidebar">
		
	</div>
	<div class="content">
		<h2>Welcome, Admin!</h2>
		<table border="1">
			<thead>
				<tr>
					<th>Property ID</th>
					<th>Property Type</th>
					<th>Sqft</th>
					<th>Furnishing</th>
					<th>Available From</th>
					<th>Rent</th>
					<th>Address</th>
					<th>Posted On Date</th>
					<th>location</th>
					<th>EB Bill</th>
					<th>Images</th>
					<th>Approval</th>
					<!-- New column for images -->
					<!-- Add more columns if needed -->
				</tr>
			</thead>
			<tbody>
				<%
                try {
                    PropertyRentLeaseDAO propertyLeaseDAO = new PropertyRentLeaseDAO();
                    List<SellerPropertyForm> propertyDetailsList = propertyLeaseDAO.getPropertyDetails();
                    if (propertyDetailsList != null) {
                        for (SellerPropertyForm property : propertyDetailsList) {
                            List<PropertyImage> propertyImages = propertyLeaseDAO.getPropertyImages(property.getPropertyId());
                %>
				<tr>
					<td><%= property.getPropertyId() %></td>
					<td><%= property.getPropertyType() %></td>
					<td><%= property.getSqft() %></td>
					<td><%= property.getFurnishing() %></td>
					<td><%= property.getAvailableFrom() %></td>
					<td><%= property.getRent() %></td>
					<td><%= property.getAddress() %></td>
					<td><%= property.getPostedOnDate() %></td>
					<td><%= property.getLocation() %></td>







					<td>
						<%
                        InputStream ebBillStream = property.getEbBillStream();
                        byte[] ebBillBytes = new byte[ebBillStream.available()];
                        ebBillStream.read(ebBillBytes);
                        String ebBillBase64 = Base64.getEncoder().encodeToString(ebBillBytes);
                        %> <img
						src="data:image/jpeg;base64,<%= ebBillBase64 %>" width="200"
						height="100" />
					</td>
					<td>
						<%-- Loop through property images and display them --%> <% for (PropertyImage image : propertyImages) { %>
						<img
						src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(image.getImage()) %>"
						width="100" height="100" /> <% } %>
					</td>
				
				 <td><form action="AdminDashboard" method="post">
				 <input type="hidden" name="propertyId" value="<%= property.getPropertyId() %>">
				 
				 <button type="submit">Approval</button>
				 
				 </form>
				
				 </td>
					
				
						
				</tr>
				
			
			
				<% 
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                %>
			</tbody>
		</table>
	</div>
</body>
</html>
