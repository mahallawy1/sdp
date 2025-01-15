package MODEL.Patterns.facade;

import java.util.List;

public class NotificationFacade {
    private final EmailService emailService;

    // Constructor that accepts an EmailService instance (Dependency Injection)
    public NotificationFacade(EmailService emailService) {
        this.emailService = emailService;
    }

    // Default constructor to use a new EmailService instance
    public NotificationFacade() {
        this.emailService = new EmailService();
    }

    // Method to send thank-you email to a list of recipients
    public void sendThankYouEmails(List<String> recipientEmails, double donationAmount) {
        String subject = "Thank You for Your Donation!";
        String body = "Dear user,\n\nThank you for donating $" + donationAmount + " to our bookstore!" +
                " Your support means the world to us.\n\nBest regards,\nBookstore Team";

        // Loop through the list of recipient emails and send the email
        for (String recipientEmail : recipientEmails) {
            emailService.sendEmail(recipientEmail, subject, body);
            System.out.println("Thank you email sent to: " + recipientEmail);
        }
    }
}
