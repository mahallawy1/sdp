package Controller;

import MODEL.DAO.UserDAO;
import MODEL.Patterns.Observer.*;
import MODEL.Patterns.facade.NotificationFacade;
import View.UserView;

// Main/Library.java
public class testLibrary {
    static EventSubject eventSubj = new EventSubject();
    static DonationSubject donationSubj = new DonationSubject();
    static DonationObserver donationObsrv = new DonationObserver(donationSubj);
    static EventObserver eventObsrv = new EventObserver(eventSubj);
    static EventObserver4Volunteer eventObsrv4Volunteer = new EventObserver4Volunteer(eventSubj);

    public static void main(String[] args) {
        // Instantiate UserDAO
        UserDAO userDAO = new UserDAO();
        System.out.println("test");
        // Create the UserController and pass the UserDAO to it
        UserController userController = new UserController(userDAO, null);  // Temporarily passing null for UserView

        // Now create UserView, but pass the userController
        UserView userView = new UserView(userController);

        // Set the UserView instance in the UserController
        userController.setUserView(userView);  // Assuming you have a setter method in UserController

        try {
            // Start by displaying the login menu
            userController.handleUserMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

       // NotificationFacade notificationFacade = new NotificationFacade();
        // notificationFacade.sendThankYouEmail("recipient@example.com", 50.0);

    }
}
