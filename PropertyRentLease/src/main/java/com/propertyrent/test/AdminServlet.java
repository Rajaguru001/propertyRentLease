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
        
     
        List<SellerPropertyForm> approvedProperties = null;
        try {
            approvedProperties = propertyLeaseDAO.getApprovedProperties();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        

        request.setAttribute("approvedProperties", approvedProperties);
        
        
        request.getRequestDispatcher("ApprovedProperty.jsp").forward(request, response);
}
    
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
