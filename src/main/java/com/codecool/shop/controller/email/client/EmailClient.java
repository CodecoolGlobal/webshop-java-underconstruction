package com.codecool.shop.controller.email.client;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {
    private static final String senderEmail = "shaman.shop99@gmail.com";
    private static final String senderPassword = "shamanshop0@Q";
    private static final String host = "smtp.gmail.com";
    private static final String port = "587";

    public static void sendAsHtml(String to, String title, String html) throws MessagingException {

        System.out.println("Sending email to " + to);

        Session mailSession = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(mailSession);
        prepareEmailMessage(message, to, title, html);
        System.out.println("Message prepared.");
        //sending message
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(host, senderEmail, senderPassword);
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("Done");
    }

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        
            message.setContent(html, "text/html; charset=utf-8");
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(title);
    }

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", port);//Outgoing port
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.starttls.enable", "true");//TLS must be activated

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }
}
