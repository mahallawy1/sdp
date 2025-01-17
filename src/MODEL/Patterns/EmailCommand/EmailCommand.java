package MODEL.Patterns.EmailCommand;

import MODEL.Patterns.facade.NotificationFacade;

import java.util.List;




import java.util.List;

public class EmailCommand implements Command {
    private List<String> adminEmails; // List of admin email addresses
    private Double donationAmount;    // Donation amount as a String
    private String donorName;         // Name of the donor
    private NotificationFacade notificationFacade;

    // Constructor
    public EmailCommand(List<String> adminEmails, Double donationAmount, String donorName, NotificationFacade notificationFacade) {
        this.adminEmails = adminEmails;
        this.donationAmount = donationAmount;
        this.donorName = donorName;
        this.notificationFacade = notificationFacade; // Initialize notificationFacade
    }

    @Override
    public void execute() {
        // Now we call the sendEmailToAdminAboutDonation method
        new Thread(()->{
            notificationFacade.sendEmailToAdminAboutDonation(adminEmails, donationAmount, donorName);
        }).start();
    }
}



