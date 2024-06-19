package com.propertyrent.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.SellerPropertyForm;

/**
 * Servlet implementation class PropertyRentSearch
 */
@WebServlet("/PropertyRentSearch")
public class PropertyRentSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyRentSearch() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  String location = request.getParameter("location");
	        String budgets =(request.getParameter("budget"));
	        

	        PropertyRentLeaseDAO propertyDAO = new PropertyRentLeaseDAO();
	        List<SellerPropertyForm> filteredProperties = null;
			try {
				int budget=Integer.parseInt(budgets);
				filteredProperties = propertyDAO.searchApprovedProperties(location, budget);
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}

	        request.setAttribute("filteredProperties", filteredProperties);
	        request.getRequestDispatcher("ApprovedProperty.jsp").forward(request, response);
	    }
	}


