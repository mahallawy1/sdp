package MODEL.Patterns.RoleHandlerStrategy;
import MODEL.DTO.User.UserDTO;
import View.UserView;

public class MemberRoleHandlerStrategy implements RoleHandlerStrategy {
    @Override
    public void processChoice(int choice, UserDTO loggedInUser ,  UserView userView) {
        switch (choice) {
            case 1:
                // Member-specific logic for "Add Donation"
                break;
            case 2:
                // Member-specific logic for "Logout"
                break;
            // Add more cases for member-specific operations
            default:
                break;
        }
    }
}

