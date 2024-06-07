package com.propertyrent.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.PropertyImage;
import com.propertyrent.model.SellerPropertyForm;

@WebServlet("/AdminDashboard")
@MultipartConfig
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PropertyRentLeaseDAO propertyLeaseDAO = new PropertyRentLeaseDAO();
        
        // Retrieve property details from DAO
        List<SellerPropertyForm> propertyDetailsList=null;
        List<PropertyImage>propertyimages=null;
		try {
			propertyDetailsList = propertyLeaseDAO.getPropertyDetails();
			
			
			System.out.println("the value are:"+propertyDetailsList);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
        
        // Set data as request attribute
        request.setAttribute("propertyDetailsList", propertyDetailsList);
        
        // Forward to admin JSP
        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int propertyIdStr = Integer.parseInt(request.getParameter("propertyId"));
        if (propertyIdStr != 0)  {
//            int propertyId = Integer.parseInt(propertyIdStr);
//            boolean isapproval=Boolean.parseBoolean(request.getParameter("isapproval"));
            PropertyRentLeaseDAO propertyDAO = new PropertyRentLeaseDAO();
            try {
                propertyDAO.approveProperty(propertyIdStr);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                // Handle exception
            }
        }
        // Redirect to doGet for displaying updated data
        response.sendRedirect("ContentPage.jsp");
    }
}
