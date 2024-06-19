package com.propertyrent.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.SellerDashBoardRequest;
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	@Override
	 */
	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SellerDashBoardRequest sdbr=new SellerDashBoardRequest();
		PropertyRentLeaseDAO prl=new PropertyRentLeaseDAO();
		List<SellerDashBoardRequest>sellerdashboardrequest=null;
		int buyerid=Integer.parseInt(request.getParameter("id"));
		int propertyid=Integer.parseInt(request.getParameter("propertyId"));
		try {
			PropertyRentLeaseDAO.buyer(buyerid,propertyid);
			String email=request.getParameter("buyerId");
			String subject=" Important Notice: Regarding Your Recent Inquiry on Elite Rentals";
			String body="Thank you for your interest in Elite Rentals. We appreciate your inquiry and are delighted to assist you in finding the perfect rental solution. However, we want to ensure that your experience is safe and secure.\r\n"
					+ "\r\n"
					+ "It has come to our attention that some users may be encountering fraudulent activities online. Therefore, we would like to emphasize the importance of caution when conducting transactions. Elite Rentals does not endorse or facilitate online payments for rental transactions. We strongly advise against sending money online to any party claiming to be associated with us.\r\n"
					+ "\r\n"
					+ "For your safety and security, we recommend connecting directly with the seller for further discussions and arrangements. Direct meetings provide an opportunity for clear communication and transparency, ensuring a smooth rental process.\r\n"
					+ "\r\n"
					+ "Should you require any assistance or have further questions, please do not hesitate to contact us. Our team is here to support you every step of the way.\r\n"
					+ "\r\n"
					+ "Thank you for choosing Elite Rentals. We look forward to assisting you in finding your ideal rental property.\r\n"
					+ "\r\n"
					+ "Best regards, ";
			EmailUtility.sendEmail(email,subject,body);
			
			
			String ownerid=request.getParameter("sellerId");
			int ownerId =Integer.parseInt(ownerid);
			String ownermailid=prl.owneremailid(ownerId);
			String subject1="Connection Details: Elite Rentals Inquiry ";
			String body1="Thank you for your interest in our rental property listed on Elite Rentals. We appreciate your inquiry and would like to facilitate your connection with the seller.\r\n"
					+ "\r\n"
					+ "Here are the contact details of the seller:\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "We advise you to reach out directly to the seller to arrange a meeting and discuss the rental terms in detail. Please remember to exercise caution and avoid online transactions for your security.\r\n"
					+ "\r\n"
					+ "If you have any further questions or need assistance, feel free to contact us. We are here to ensure a smooth and secure rental process for you.\r\n"
					+ "\r\n"
					+ "Best regards,";
			EmailUtility.sendEmail(ownermailid,subject1,body1);
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		
		int ownersid=Integer.parseInt(request.getParameter("sellerId"));
		int buyersid=Integer.parseInt(request.getParameter("id"));
		int propertesid=Integer.parseInt(request.getParameter("propertyId"));
		
		sdbr.setOwner_id(ownersid);
		sdbr.setRent_id(buyersid);
		sdbr.setProperty_id(propertesid);
		
		try {
			PropertyRentLeaseDAO.buyerrequest(ownersid,buyersid,propertesid);
			sellerdashboardrequest = PropertyRentLeaseDAO.sellerdashboard(buyersid);
			
		    request.setAttribute("sellerdashboardrequest", sellerdashboardrequest);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	  
		
	}

}
