package MODEL.Patterns.RoleHandlerStrategy;

import Controller.UserController;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Invoker;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.InputHandler;
import View.UtilityHandler;

import java.sql.SQLException;

public class VolunteerRoleHandlerStrategy implements RoleHandlerStrategy {

    private final UserController userController;
    private Invoker invoker;

    public VolunteerRoleHandlerStrategy(UserController userController) {
        this.userController = userController;
        this.invoker = new Invoker();
    }

    @Override
    public boolean processChoice(int choice, UserDTO loggedInUser, UtilityHandler ui,InputHandler inputHandler) throws SQLException {
        switch (choice) {
            case 1:
                // Volunteer-specific logic for "Add Donation"
                userController.processDonation(loggedInUser);
                break;
            case 2:
                // Volunteer-specific logic for "Create Event"
                userController.createEvent(loggedInUser);

                break;
            case 3:
                // Volunteer-specific logic for "Delete Event"

                userController.deleteEvent();
                break;
            case 4:
                userController.joinEvent(loggedInUser);
                break;


            case 5:

                ui.showMessage("Logging out...");
                return true; // This will exit the menu loop
            case 6:
                ui.showMessage("Exiting...");//
                DbConnectionSingleton.getInstance().close(null, null);
                System.exit(0);
                break;

            default:
                break;
        }
        return false;
    }


}

