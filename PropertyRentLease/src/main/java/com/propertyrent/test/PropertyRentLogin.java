package com.propertyrent.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.UsersInfo;

/**
 * Servlet implementation class PropertyRentDetails
 */
@WebServlet("/PropertyRentLogin")
public class PropertyRentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyRentLogin() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String phonenumber=request.getParameter("phonenumber");
		UsersInfo User= new UsersInfo(username,password, email,phonenumber);
		System.out.println(User.getEmail());
		PropertyRentLeaseDAO prl=new PropertyRentLeaseDAO();
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "Login":
				try {		
						UsersInfo userId=prl.getUserIdByEmail(User);
						HttpSession session = request.getSession();
					
						session.setAttribute("user", userId);
						
						UsersInfo adminlogincheck = prl.adminlogincheck(userId);
						if(adminlogincheck!=null) {
							if(User.getEmail().matches("\\b[A-Za-z0-9._%+-]+@eliterental\\.com\\b") && User.getPassword().matches("Raju@123")) {
								response.sendRedirect("AdminDashBoard.jsp");
							}
							else {
								response.sendRedirect("ContentPage.jsp");
								
							}
						}
						else {
							response.sendRedirect("RegisterPage.jsp");
						}		
					
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				break;
			case "Register":
				try {
					if(prl.insert(User)) {
						 response.sendRedirect("Homepage.jsp");
					}
					else {
						response.sendRedirect("Homepage.jsp");
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				 
				
				break;
				default:
					
					break;
		}
			
			  if ("PostProperty".equals(action)) {
		            HttpSession session = request.getSession(false);
		        	UsersInfo adminlogincheck=(UsersInfo) session.getAttribute("user");
		        	
		            if (session != null && session.getAttribute("user") != null) {
		            
			            try {
			            UsersInfo ownerId=PropertyRentLeaseDAO.checkseller(adminlogincheck);
			            
			            if (ownerId!= null) {
			                response.sendRedirect("SellerDashBoard.jsp");
			            } else {
			                response.sendRedirect("PostProperty.jsp");
			            }
			            
			            }
			            catch (ClassNotFoundException | SQLException e) {
			                e.printStackTrace(); 
			            }
			            
		               
		            	
		            } 
		            else {
		              
		                response.sendRedirect("Homepage.jsp");
		            }
		            return;
		        }
		
	}


}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		
	}

}
