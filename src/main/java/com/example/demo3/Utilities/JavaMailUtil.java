package com.example.demo3.Utilities;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.ResourceBundle;

public class JavaMailUtil {
    public static void sendMailReg(String recipient, String subject, String htmlTextMessage, String verifyLoginLink) throws MessagingException {
        Properties properties = new Properties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.host", host);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        ResourceBundle rb = ResourceBundle.getBundle("app");
        String emailId = rb.getString("mailer.email");
        String password = rb.getString("mailer.password");

        properties.put("mail.user", emailId);
        properties.put("mail.password", password);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailId, password);
            }
        });

        Message message = prepareMessageReg(session, emailId, recipient, subject, htmlTextMessage, verifyLoginLink);
        Transport.send(message);
    }

    public static Message prepareMessageReg(Session session, String emailId, String recipientEmail,
                                            String subject, String htmlTextMessage, String verifyLink) {
        // executed by sendMailReg()
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailId));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Register Verification");
            message.setText("Hello " + recipientEmail + "!" + " " +
                    "Click the link below to verify your account: http://localhost:8080/active.jsp?loginlink=" + verifyLink); //Will fix later

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
