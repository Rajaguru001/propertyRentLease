package com.propertyrent.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.Comments;

/**
 * Servlet implementation class PropertyRentComment
 */
@WebServlet("/PropertyRentComment")
public class PropertyRentComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyRentComment() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PropertyRentLeaseDAO prl=new PropertyRentLeaseDAO();
		Comments comments=new Comments();
		int userid=Integer.parseInt(request.getParameter("id"));
		String comment=request.getParameter("comment");
		int propertyid=Integer.parseInt(request.getParameter("propertyId"));
		System.out.println("the userid"+userid);
		System.out.println("the comment"+comment);
		System.out.println("the propertyid"+propertyid);
		comments.setUser_id(userid);
		comments.setComment_section(comment);
		comments.setProperty_id(propertyid);
		try {
			prl.getcomment(propertyid);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	
		
		try {
			prl.comment(userid,comment,propertyid);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		

		
		
		
	}

}
