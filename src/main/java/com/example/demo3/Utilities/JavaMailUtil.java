package com.example.demo3.Utilities;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.ResourceBundle;

public class JavaMailUtil {
    public static void sendMail(String name) throws MessagingException {
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

        //Message message = prepareMessage(session, emailId, password);
        //Transport.send(message);
    }

    public static Message prepareMessage(Session session, String emailId, String recipientEmail, String verifyLink) {
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailId));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Register Verification");
            message.setText("Hello " + recipientEmail + "!" + " " +
                    "Click the link below to verify your account: " + verifyLink);

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
