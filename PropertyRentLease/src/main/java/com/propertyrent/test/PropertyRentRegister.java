package com.propertyrent.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.model.UsersInfo;

/**
 * Servlet implementation class PropertyRentRegister
 */
@WebServlet("/PropertyRentRegister")
public class PropertyRentRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyRentRegister() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username=request.getParameter("name");
		String password=request.getParameter("Password");
		String email=request.getParameter("email");
		String phonenumber=request.getParameter("phonenumber");
		UsersInfo User= new UsersInfo(username,password, email,phonenumber);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
