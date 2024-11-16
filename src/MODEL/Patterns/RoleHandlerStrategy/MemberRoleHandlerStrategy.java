package MODEL.Patterns.RoleHandlerStrategy;
import Controller.UserController;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.UserView;

public class MemberRoleHandlerStrategy implements RoleHandlerStrategy {
     private final UserController userController;
    
    public MemberRoleHandlerStrategy(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean processChoice(int choice, UserDTO loggedInUser , UserView userView) {
        switch (choice) {
            case 1:
                // Member-specific logic for "Add Donation"
                                               userController.processDonation(loggedInUser);

                break;
            case 2:
                // Member-specific logic for "Logout"
                 userView.showMessage("Logging out...");
                return true;
            case 3:
                System.out.println("Exiting...");
                DbConnectionSingleton.getInstance().close(null, null);
                System.exit(0);
                break;
            // Add more cases for member-specific operations
            default:
                break;
        }
        return false;
    }
}

