package com.propertyrent.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propertyrent.dao.PropertyRentLeaseDAO;
import com.propertyrent.model.UsersInfo;

@WebServlet("/PropertyRentSubscription")
public class PropertyRentSubscription extends HttpServlet {
	PropertyRentLeaseDAO prl=new PropertyRentLeaseDAO();
    private static final long serialVersionUID = 1L;
       
    public PropertyRentSubscription() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ownerId = Integer.parseInt(request.getParameter("id"));
        String userID = request.getParameter("userID");
        String cardNumber = request.getParameter("cardNumber");
        String expiry = request.getParameter("expiry");
        String cvv = request.getParameter("cvv");
        boolean paymentStatus = Boolean.parseBoolean(request.getParameter("status"));

        UsersInfo userInfo = new UsersInfo();
        userInfo.setPaymentStatus(paymentStatus);
        
        prl.subscriptionInsert(ownerId, userID, cardNumber, expiry, cvv);
        
        
        System.out.println("Owner ID: " + ownerId);
        System.out.println("UserID: " + userID);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiry: " + expiry);
        System.out.println("CVV: " + cvv);
        System.out.println("Payment Status: " + paymentStatus);

        response.sendRedirect("ContentPage.jsp");
    }
}
