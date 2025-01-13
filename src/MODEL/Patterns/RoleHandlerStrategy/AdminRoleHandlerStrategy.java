package MODEL.Patterns.RoleHandlerStrategy;
import Controller.UserController;
import MODEL.DAO.AddressDAO;
import MODEL.DAO.RoleDAO;
import MODEL.DAO.UserDAO;
import MODEL.DTO.User.AddressDTO;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.UserView;

import java.sql.SQLException;
import java.util.List;

public class AdminRoleHandlerStrategy implements RoleHandlerStrategy {
    
    private final UserController userController;
    
    public AdminRoleHandlerStrategy(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean processChoice(int choice, UserDTO loggedInUser , UserView userView) throws SQLException {
        String EmailIpnut = null;
        String PasswordInput= null;
        String firstNameIpnut = null;
        String mobilePhoneInput = null;
        String addressIdInput = null;
        String roleIdInput = null;
        String statusInput = null;
        int userId = 0;

        switch (choice) {
            case 1:
                // Signup logic
                 EmailIpnut = userView.getInputWithValidation("Enter email: ", "email");
                 PasswordInput= userView.getInputWithValidation("Enter password: ", "password");
                 firstNameIpnut = userView.getInputWithValidation("Enter first name: ", "text");
                 mobilePhoneInput = userView.getInputWithValidation("Enter mobile phone: ", "phone");
                 addressIdInput = userView.getInputWithValidation("Enter address ID: ", "addressid");
                 roleIdInput = userView.getInputWithValidation("Are you An volunteer or a member chose 1 or 2 respectively: ", "role");
                 statusInput = userView.getInputWithValidation("enter the status: ", "status");

                UserDTO newUser = new UserDTO();

                // Set user data
                newUser.setEmail(EmailIpnut);
                newUser.setPassword(PasswordInput);
                newUser.setFirstname(firstNameIpnut);
                newUser.setMobilePhone(mobilePhoneInput);

                // Converting addressId and roleId from String to Integer
                int addressId = Integer.parseInt(addressIdInput);
                int roleId = Integer.parseInt(roleIdInput);
                roleId = roleId + 1;
                int status = Integer.parseInt(statusInput);
                // Set these values in the UserDTO
                newUser.setAddressId(addressId);
                newUser.setRoleId(roleId);
                newUser.setStatus(status);

                boolean isAdded = UserDAO.addUser(loggedInUser);//
                userView.showMessage("User added: " + isAdded);
                break;

            case 2:
                // Admin-specific logic for "Retrieve User by ID"
                // Retrieve User by ID
                String userIdInput = userView.getInputWithValidation("enter userId : ", "userId");
                 userId = Integer.parseInt(userIdInput);

                UserDTO retrievedUser = UserDAO.getUserById(userId);

                if (retrievedUser != null) {
                    // Fetch Address and Role
                    AddressDTO address = AddressDAO.getAddressById(retrievedUser.getAddressId());
                    RoleDTO role = RoleDAO.getRoleById(retrievedUser.getRoleId());
//
                    userView.showMessage("User ID: " + retrievedUser.getId());
                    userView.showMessage("Name: " + retrievedUser.getFirstname());
                    userView.showMessage("Email: " + retrievedUser.getEmail());
                    userView.showMessage("Mobile Phone: " + retrievedUser.getMobilePhone());
                    userView.showMessage("Status: " + retrievedUser.getStatus());
                    userView.showMessage("Role: " + (role != null ? role.getName() : "Role not found"));
                    userView.showMessage("Address: " + (address != null ? address.getName() : "Address not found"));
                } else {
                    userView.showMessage("User not found.");
                }
                break;
            case 3:
                // Update User
                 userIdInput = userView.getInputWithValidation("enter userId: ", "userId");
                userId = Integer.parseInt(userIdInput);

                UserDTO userToUpdate = UserDAO.getUserById(userId);

                if (userToUpdate != null) {


                     firstNameIpnut= userView.getInputWithValidation("Enter name : ", "text");
                     userToUpdate.setFirstname(firstNameIpnut);
                     PasswordInput= userView.getInputWithValidation("Enter password: ", "password");
                     userToUpdate.setPassword(PasswordInput);
                     EmailIpnut = userView.getInputWithValidation("Enter email: ", "email");
                     userToUpdate.setEmail(EmailIpnut);
                     mobilePhoneInput = userView.getInputWithValidation("Enter Mobile number: ", "phone");
                     userToUpdate.setMobilePhone(mobilePhoneInput);

                     statusInput = userView.getInputWithValidation("Enter status : ", "status");
                     userToUpdate.setStatus(Integer.parseInt(statusInput));

//
                    boolean isUpdated = UserDAO.updateUser(userToUpdate);
                    userView.showMessage("User updated: " + isUpdated);
                } else {
                    userView.showMessage("User not found.");//
                }
                break;
            case 4:
                // ANSI escape codes for colors
                  // Reset color

// Admin-specific logic for "Retrieve All Users"
// Retrieve All Users
                List<UserDTO> users = UserDAO.getAllUsers();//
                userView.showMessage("All users:");

// Print a header for the table with blue color//
                String format = "%-10s %-20s %-30s %-15s %-15s %-20s %-50s%n";
userView.displayTableHeader(format, "User ID", "Name", "Email", "Mobile Phone", "Status", "Role", "Address");
                
               

// Print a separator line//
                userView.showMessage("-------------------------------------------------------------------------------------------------------------------------------------------------");

                for (UserDTO user : users) {
                    // Fetch Address and Role for each user
                    String userAddress = AddressDAO.getFullAddressPath(user.getAddressId());
                    RoleDTO userRole = RoleDAO.getRoleById(user.getRoleId());
//
                    // Print each user's details in a formatted way
                     format = "%-10d %-20s %-30s %-15s %-15s %-20s %-50s%n";
userView.displayTableRow(
    format,
    user.getId(),
    user.getFirstname(),
    user.getEmail(),
    user.getMobilePhone(),
    user.getStatus(),
    (userRole != null ? userRole.getName() : "Role not found"),
    (userAddress != null ? userAddress : "Address not found")
);
                    
                   
                }
//
                userView.showMessage("**********************************************************");

                break;
            case 5:
                       ////////delete user here
               userController.deleteusr();
                break;
            case 6:
               userController.processDonation(loggedInUser);

                break;
            case 7:
                userController.createEvent(loggedInUser);

                break;

            case 8:
               userController.deleteEvent();
                break;
                
            case 9:
                userController.addBook();
                break;
                
            case 10:
                userController.deleteBook();
                break;
            case 11:
                // Volunteer-specific logic for "Logout"
                userView.showMessage("Logging out...");
                return true;

            case 12://
                userView.showMessage("Exiting...");
                DbConnectionSingleton.getInstance().close(null, null);//
                System.exit(0);
                break;
            // Add more cases for admin-specific operations
            default:
                break;
        }
        return false;
    }
}
