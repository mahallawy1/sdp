package MODEL.Patterns.Observer;

import MODEL.DAO.UserDAO;
import MODEL.Patterns.EmailCommand.EmailCommand;
import MODEL.Patterns.facade.NotificationFacade;

import java.sql.SQLException;

public class DonationSubject extends ASubject{
    protected String donorName;
    protected Double donationAmount;

    public void setNotification(String donorName, Double donationAmount){
        this.donorName = donorName;
        this.donationAmount = donationAmount;
        // use notification facade to send emails to admins
        NotificationFacade notificationFacade = new NotificationFacade();
        try {
            EmailCommand emailCommand = new EmailCommand(UserDAO.getAdminEmails(),donationAmount, donorName, notificationFacade);
            emailCommand.execute();
        }catch (SQLException e){

        }
        super.notifyObservers();
    }
}