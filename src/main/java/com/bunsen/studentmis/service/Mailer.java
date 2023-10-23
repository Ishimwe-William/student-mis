package com.bunsen.studentmis.service;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Mailer {
    private String smtpPort;
    private String smtpHost;
    private String smtpUsername;
    private String smtpPassword;
    private String otp;

    public Mailer() {
        this.smtpHost = "smtp.gmail.com";
        this.smtpPort = "587";
        this.smtpUsername = "ishimwe.william2000@gmail.com";
        this.smtpPassword = "aywnygvpjwoxpvvm";
    }

    public String sendEmail(String recipientEmail,String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.debug", "true");

        jakarta.mail.Authenticator auth = new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(smtpUsername));
            InternetAddress[] address = {new InternetAddress(recipientEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("AUCA Registration Email");
            otp = new DecimalFormat("0000").format(new Random().nextInt(9999));
            msg.setText(body+ "\n\n" +
                    "Sent to: " + recipientEmail + "\n" +
                    "Sent on: " + new Date().toString());

            Transport.send(msg);

        } catch (jakarta.mail.MessagingException ex) {
            ex.printStackTrace();
            return null;
        }
        return otp;
    }
}
