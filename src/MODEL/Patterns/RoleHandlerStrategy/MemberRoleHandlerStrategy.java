package MODEL.Patterns.RoleHandlerStrategy;
import Controller.UserController;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.InputHandler;
import View.UserView;
import View.UtilityHandler;
import java.sql.SQLException;

public class MemberRoleHandlerStrategy implements RoleHandlerStrategy {
     private final UserController userController;
    
    public MemberRoleHandlerStrategy(UserController userController) {
        this.userController = userController;
    }
    @Override
   public boolean processChoice(int choice, UserDTO loggedInUser, UtilityHandler ui,InputHandler inputHandler) throws SQLException {
        switch (choice) {
            case 1:
                // Member-specific logic for "Add Donation"
                                               userController.processDonation(loggedInUser);

                break;
            case 2:
                userController.displayAvailableBooks();
                break;
            case 3:
                userController.borrowBook(loggedInUser);
                break;
          
            case 4:
                // Member-specific logic for "Logout"
                 ui.showMessage("Logging out...");
                return true;
            case 5:
                ui.showMessage("Exiting...");//
                DbConnectionSingleton.getInstance().close(null, null);
                System.exit(0);//
                break;
            // Add more cases for member-specific operations
            default:
                break;
        }
        return false;
    }
}

