package com.propertyrent.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {
    
    private EmailUtility() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        String fromEmail = "nrajaguru24@gmail.com"; 
        String password = "tozofskpidfsbkiw"; 
      

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(fromEmail, password);
            }
        });

       
        String htmlContent = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
             
                "<title>EliteRentals - Welcome</title>" +
                "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">" +

                "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">" +
                "<style>" +
                ".header img { width: 60px; margin-right: 10px; }" +
                ".social-icons .fa { font-size: 30px; margin: 0 10px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"header bg-light py-3 d-flex align-items-center justify-content-center\">" +
                "<img src=\"https://codewithsadee.github.io/cryptex/assets/images/logo.svg\" alt=\"Company Logo\">" +
                "<h3 class=\"mb-0\">EliteRental</h3>" +
                "</div>" +
                "<div class=\"content text-center py-5\">" +
                "<div class=\"container\">" +
                "<h2>Hey buddy!</h2>"+
                "<p>Certainly! Here's the message condensed:\r\n"
                + "\r\n"
                + "\"We appreciate your interest in Elite Rentals and are committed to ensuring your safety and security. We've noticed potential fraudulent activities online and want to caution against making payments to parties claiming association with us. Instead, we recommend direct communication with sellers to facilitate transparent transactions. Should you need assistance or have questions, please reach out to us. Thank you for choosing Elite Rentals; we're dedicated to helping you find the perfect rental property.\"</p>" +
                "<a href=\"#\" class=\"btn btn-primary\">Explore Now</a>" +
                "</div>" +
                "</div>" +
                "<div class=\"footer bg-light py-3 text-center\">" +
                "<p class=\"mb-2\">Follow us on:</p>" +
                "<div class=\"social-icons\">" +
                "<a href=\"https://facebook.com/yourcompany\"><i class=\"fab fa-facebook\"></i></a>" +
                "<a href=\"https://twitter.com/yourcompany\"><i class=\"fab fa-twitter\"></i></a>" +
                "<a href=\"https://linkedin.com/yourcompany\"><i class=\"fab fa-linkedin\"></i></a>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setContent(htmlContent, "text/html");

       
        Transport.send(message);
    }
}