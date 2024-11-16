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

                boolean isAdded = UserDAO.addUser(loggedInUser);
                System.out.println("User added: " + isAdded);
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

                    System.out.println("User ID: " + retrievedUser.getId());
                    System.out.println("Name: " + retrievedUser.getFirstname());
                    System.out.println("Email: " + retrievedUser.getEmail());
                    System.out.println("Mobile Phone: " + retrievedUser.getMobilePhone());
                    System.out.println("Status: " + retrievedUser.getStatus());
                    System.out.println("Role: " + (role != null ? role.getName() : "Role not found"));
                    System.out.println("Address: " + (address != null ? address.getName() : "Address not found"));
                } else {
                    System.out.println("User not found.");
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


                    boolean isUpdated = UserDAO.updateUser(userToUpdate);
                    System.out.println("User updated: " + isUpdated);
                } else {
                    System.out.println("User not found.");
                }
                break;
            case 4:
                // ANSI escape codes for colors
                String BLUE = "\033[34m";   // Blue color
                String RESET = "\033[0m";   // Reset color

// Admin-specific logic for "Retrieve All Users"
// Retrieve All Users
                List<UserDTO> users = UserDAO.getAllUsers();
                System.out.println("All users:");

// Print a header for the table with blue color
                System.out.printf(BLUE + "%-10s %-20s %-30s %-15s %-15s %-20s %-50s%n" + RESET,
                        "User ID", "Name", "Email", "Mobile Phone", "Status", "Role", "Address");

// Print a separator line
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

                for (UserDTO user : users) {
                    // Fetch Address and Role for each user
                    String userAddress = AddressDAO.getFullAddressPath(user.getAddressId());
                    RoleDTO userRole = RoleDAO.getRoleById(user.getRoleId());

                    // Print each user's details in a formatted way
                    System.out.printf("%-10d %-20s %-30s %-15s %-15s %-20s %-50s%n",
                            user.getId(),
                            user.getFirstname(), // Assuming a 'lastname' field
                            user.getEmail(),
                            user.getMobilePhone(),
                            user.getStatus(),
                            (userRole != null ? userRole.getName() : "Role not found"),
                            (userAddress != null ? userAddress : "Address not found"));
                }

                System.out.println();

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
                // Volunteer-specific logic for "Logout"
                userView.showMessage("Logging out...");
                return true;

            case 10:
                System.out.println("Exiting...");
                DbConnectionSingleton.getInstance().close(null, null);
                System.exit(0);
                break;
            // Add more cases for admin-specific operations
            default:
                break;
        }
        return false;
    }
}
