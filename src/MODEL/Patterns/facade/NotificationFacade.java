package MODEL.Patterns.facade;

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

    public void sendThankYouEmail(String recipientEmail, double donationAmount) {
        String subject = "Thank You for Your Donation!";
        String body = "Dear user,\n\nThank you for donating $" + donationAmount + " to our bookstore!" +
                " Your support means the world to us.\n\nBest regards,\nBookstore Team";

        // Using the email service to send the email
        emailService.sendEmail(recipientEmail, subject, body);
    }
}
