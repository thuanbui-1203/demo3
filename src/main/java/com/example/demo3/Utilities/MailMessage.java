package com.example.demo3.Utilities;

import jakarta.mail.MessagingException;

public class MailMessage {
    public static void registrationSuccess(String name, String emailId, String verifyLoginLink) {
        String recipient = emailId;
        String subject = "Registration Successful";
        String htmlTextMessage = "<html>" + "<body>"
                + "<h2 style='color:green;'>Welcome to Thuan Bui Electronics</h2>" + "Hi " + name + ","
                + "<br><br>As a Welcome gift for our New Customers we are providing additional 10% OFF Upto 500.000 VNDs for the first product purchase. "
                + "<br>To avail this offer you only have "
                + "to enter the promo code given below.<br><br><br> PROMO CODE: " + "BUITHUANEWE<br><br><br>"
                + "Have a good day!<br>"
                + "Click the link below to verify your account: http://localhost:8080/active.jsp?loginlink=" + verifyLoginLink
                + "</body>" + "</html>";
        try {
            JavaMailUtil.sendMailReg(recipient, subject, htmlTextMessage, verifyLoginLink);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
