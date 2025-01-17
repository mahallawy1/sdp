package MODEL.Patterns.facade;
import View.UtilityHandler;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private final String senderEmail = "thirdpartydevtool@gmail.com"; // Replace with your email
    private final String senderPassword = "wmjg kqcn zgsh abei";      // Replace with your email password
    private UtilityHandler UI = new UtilityHandler();
    public void sendEmail(String recipientEmail, String subject, String body) {
        // Step 1: Configure SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Step 2: Create a mail session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Step 3: Construct the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Step 4: Send the email
            Transport.send(message);
            UI.showMessage("Email sent successfully to " + recipientEmail);

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}



