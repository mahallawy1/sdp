package MODEL.Patterns.RoleHandlerStrategy;
import Controller.UserController;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.UserView;

public class VolunteerRoleHandlerStrategy implements RoleHandlerStrategy {
    
    private final UserController userController;
    
    public VolunteerRoleHandlerStrategy(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean processChoice(int choice, UserDTO loggedInUser , UserView userView) {
        switch (choice) {
            case 1:
                // Volunteer-specific logic for "Add Donation"
                               userController.processDonation(loggedInUser);
                break;
            case 2:
                // Volunteer-specific logic for "Create Event"
                break;
            case 3:
                // Volunteer-specific logic for "Delete Event"
                break;
            case 4:
                // Volunteer-specific logic for "Logout"
                userView.showMessage("Logging out...");
                return true; // This will exit the menu loop
            case 5:
                System.out.println("Exiting...");
                DbConnectionSingleton.getInstance().close(null, null);
                System.exit(0);
                break;

            default:
                break;
        }
        return false;
    }


}

