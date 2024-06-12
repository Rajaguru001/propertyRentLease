package com.propertyrent.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.util.EmailUtility;

/**
 * Servlet implementation class PropertyRentBuyer
 */
@WebServlet("/PropertyRentBuyer")
public class PropertyRentBuyer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyRentBuyer() {
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
		int buyerid=Integer.parseInt(request.getParameter("id"));
		int propertyid=Integer.parseInt(request.getParameter("propertyId"));
		try {
			prl.buyer(buyerid,propertyid);
			String email=request.getParameter("buyerId");
			String subject="Thanks for UsingElite Rentals ";
			String body="dear seller your jfewoifew";
			EmailUtility.sendEmail(email,subject,body);
			
			
			String ownerid=request.getParameter("sellerId");
			int ownerId =Integer.parseInt(ownerid);
			System.out.println("the email for seller "+ownerId);
			String ownermailid=prl.owneremailid(ownerId);
			System.out.println("the email for seller "+ownermailid);
			String subject1="Thanks for UsingElite Rentals ";
			String body1="dear Buyer your jfewoifew";
			EmailUtility.sendEmail(ownermailid,subject1,body1);
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		
		
	}

}
