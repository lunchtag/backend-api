package nl.lunchtag.resource.Lunchtag.logic.mailservice;

import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class SendEmailService {

    private final String fromEmail = "noreply.lunchtag@gmail.com";
    private final String host = "smtp.gmail.com";

    public void SendEmail(String toEmail, String pinCode, Boolean pinCodeReset) {

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, "Frikandel10!");
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromEmail));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            // Set Subject: header field
            message.setSubject(getSubject(pinCodeReset));

            // Now set the actual message
            message.setText(getMessage(pinCodeReset, toEmail, pinCode));

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    private String getSubject(Boolean pinCodeReset){
        if(pinCodeReset){
            return "Lunchtag - pincode reset";
        }

        return "Welkom bij Lunchtag!";
    }

    private String getMessage(Boolean pinCodeReset, String toEmail, String pinCode){
        if(pinCodeReset){
            return "Uw heeft een nieuwe pincode aangevraagd.\r\n" +
                    "U kunt inloggen met de volgende gegevens:\r\n" +
                    "Email: " + toEmail +
                    "\r\nPinCode: " + pinCode;
        }

        return "U bent aangemeld in het systeem.\r\n" +
                "U kunt inloggen met de volgende gegevens:\r\n" +
                "Email: " + toEmail +
                "\r\nPinCode: " + pinCode;
    }
}
