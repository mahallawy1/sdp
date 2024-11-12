package MODEL.Patterns.RoleHandlerStrategy;

import MODEL.DTO.User.UserDTO;
import View.UserView;

public class VolunteerRoleHandlerStrategy implements RoleHandlerStrategy {
    @Override
    public void processChoice(int choice, UserDTO loggedInUser ,  UserView userView) {
        switch (choice) {
            case 1:
                // Volunteer-specific logic for "Add Donation"
                break;
            case 2:
                // Volunteer-specific logic for "Create Event"
                break;
            case 3:
                // Volunteer-specific logic for "Delete Event"
                break;
            // Add more cases for volunteer-specific operations
            default:
                break;
        }
    }


}

