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
 * Servlet implementation class PropertyRentSellerDashBoard
 */
@WebServlet("/PropertyRentSellerDashBoard")
public class PropertyRentSellerDashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyRentSellerDashBoard() {
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
		String sellerproperty=request.getParameter("propertyId");
		int sellersproperty=Integer.parseInt(sellerproperty);
		 if (sellersproperty != 0) {
	       
	            try {
	                PropertyRentLeaseDAO.sellerproperty(sellersproperty);
	                
	            } catch (SQLException | ClassNotFoundException e) {
	                e.printStackTrace();
	               
	            }
	            
	            
	        }
		 SellerDashBoardRequest sdbr=new SellerDashBoardRequest();
			PropertyRentLeaseDAO prl=new PropertyRentLeaseDAO();
			List<SellerDashBoardRequest>sellerdashboardrequest=null;
			int buyerid=Integer.parseInt(request.getParameter("rentid"));
			int propertyid=Integer.parseInt(request.getParameter("propertyId"));
			String rentmail=null;
			try {
				rentmail=prl.owneremailid(buyerid);

				PropertyRentLeaseDAO.buyer(buyerid,propertyid);

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
				EmailUtility.sendEmail(rentmail,subject,body);
				response.sendRedirect(request.getContextPath() + "/SellerDashBoard.jsp");
			}
			catch (ClassNotFoundException | SQLException | MessagingException e) {

				e.printStackTrace();
			} 
		 
		 
		
	}

}
