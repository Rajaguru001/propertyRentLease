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
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PropertyRentLeaseDAO prl=new PropertyRentLeaseDAO();
		Comments comments=new Comments();
		int userid=Integer.parseInt(request.getParameter("id"));
		String comment=request.getParameter("comment");
		int propertyid=Integer.parseInt(request.getParameter("propertyId"));
		comments.setUserid(userid);
		comments.setCommentsection(comment);
		comments.setPropertyid(propertyid);
		try {
			prl.getcomment(propertyid);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	
		
		try {
			PropertyRentLeaseDAO.comment(userid,comment,propertyid);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		

		
		
		
	}

}
