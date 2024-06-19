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
        		String budgets=(request.getParameter("budget"));
        		
        		
        		
        		try {
        			int budget=Integer.parseInt(budgets);
        			approvedProperties=propertyLeaseDAO.searchApprovedProperties(location,budget);
        			 request.setAttribute("approvedProperties", approvedProperties);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
        		break;
        		
        	
        	 default:
        	        
        	        
        	        break;
        		
        		
        	}
        	
        }
        

       
        
        
        request.getRequestDispatcher("ApprovedProperty.jsp").forward(request, response);
}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String propertysId =(request.getParameter("propertyId"));
        int propertyId=Integer.parseInt(propertysId);
        if (propertyId != 0) {
            PropertyRentLeaseDAO propertyDAO = new PropertyRentLeaseDAO();
            try {
                propertyDAO.approveProperty(propertyId);
                response.sendRedirect(request.getContextPath() + "/AdminDashBoard.jsp");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
               
            }
            
        }
        
       
        
         
    }
}
