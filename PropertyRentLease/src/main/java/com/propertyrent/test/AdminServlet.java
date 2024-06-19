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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PropertyRentLeaseDAO propertyLeaseDAO = new PropertyRentLeaseDAO();
        String action=request.getParameter("search"); 
        List<SellerPropertyForm> approvedProperties = null;
     if(action==null) {
    	 
     
   
        try {
            approvedProperties = propertyLeaseDAO.getApprovedProperties();
            request.setAttribute("approvedProperties", approvedProperties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
     }
        else  {
        	switch(action){
        	case "search":
        		String location=request.getParameter("location");
        		int budget=Integer.parseInt(request.getParameter("budget"));
        		
        		
        		try {
        			approvedProperties=propertyLeaseDAO.searchApprovedProperties(location,budget);
        			 request.setAttribute("approvedProperties", approvedProperties);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
        		break;
        		
        		
        	}
        	
        }
        

       
        
        
        request.getRequestDispatcher("ApprovedProperty.jsp").forward(request, response);
}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        if (propertyId != 0) {
            PropertyRentLeaseDAO propertyDAO = new PropertyRentLeaseDAO();
            try {
                propertyDAO.approveProperty(propertyId);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
               
            }
            
        }
        response.sendRedirect("AdminDashBoard.jsp");
    }
}
