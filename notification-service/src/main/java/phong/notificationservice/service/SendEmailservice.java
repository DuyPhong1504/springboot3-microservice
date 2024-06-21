package phong.notificationservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phong.notificationservice.dto.request.SendEmailRequest;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SendEmailservice {
    // Email configuration
    private String host = "smtp.gmail.com"; // SMTP server address
    private String user = "onlinemakefriends@gmail.com"; // Sender's email
    private String password = "ckapnweiblqmuygr"; // Sender's email password

    public void sendEmailSmtpGmail(SendEmailRequest request) {
        // Recipient's email ID
        // Get system properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587"); // SMTP port, usually 587 or 465
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Get the Session object and authenticate
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            // Set From: header field
//            message.setFrom(new InternetAddress(user));
            message.setFrom(new InternetAddress(request.getFrom()));
            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getTo()));
            // Set Subject: header field
            message.setSubject(request.getSubject());
            // Now set the actual message
            message.setText(request.getContent());
            // Send message
            Transport.send(message);
            log.info("Sent message successfully....");
        } catch (MessagingException mex) {
            log.info(mex.getMessage());
        }
    }


}
