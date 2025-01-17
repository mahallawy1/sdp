package MODEL.Patterns.facade;

import View.UtilityHandler;
import java.util.List;

public class NotificationFacade {
    private final EmailService emailService;
    private UtilityHandler UI = new UtilityHandler();
    // Constructor that accepts an EmailService instance (Dependency Injection)
    public NotificationFacade(EmailService emailService) {
        this.emailService = emailService;
    }

    // Default constructor to use a new EmailService instance
    public NotificationFacade() {
        this.emailService = new EmailService();
    }

    // Method to send thank-you email to a list of recipients
    public void sendThankYouEmail(String recipientEmail, double donationAmount) {
        String subject = "Thank You for Your Donation!";
        String body = "Dear user,\n\nThank you for donating $" + donationAmount + " to our bookstore!" +
                " Your support means the world to us.\n\nBest regards,\nBookstore Team";

        emailService.sendEmail(recipientEmail, subject, body);
        UI.showMessage("Thank you email sent to: " + recipientEmail);

    }

    public void sendEmailToAdminAboutDonation(List<String> recipientEmails, double donationAmount, String donorName) {
        String subject = "New Donation!";
        String body = "Dear admin,\n\nA new donation with amount $" + donationAmount + "by donor: " +
                donorName +" was donated to our bookstore!" ;

        // Loop through the list of recipient emails and send the email
        for (String recipientEmail : recipientEmails) {
            emailService.sendEmail(recipientEmail, subject, body);
            UI.showMessage("Admin with email: " + recipientEmail + " is notified about your donation");
        }
    }
}
