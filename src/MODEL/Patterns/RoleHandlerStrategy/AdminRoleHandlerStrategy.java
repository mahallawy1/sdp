package MODEL.Patterns.RoleHandlerStrategy;
import Controller.UserController;
import MODEL.DAO.AddressDAO;
import MODEL.DAO.RoleDAO;
import MODEL.DTO.User.AddressDTO;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Cmd.AddUserCmd;
import MODEL.Patterns.Command.Cmd.*;
import MODEL.Patterns.Command.Invoker;
import MODEL.Patterns.Command.Manager.UserManager;
import MODEL.Patterns.Command.Cmd.RetrieveAllUsersCmd;
import MODEL.Patterns.Command.Cmd.RetrieveUserCmd;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.InputHandler;

import View.UtilityHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminRoleHandlerStrategy implements RoleHandlerStrategy {
    
    private final UserController userController;
    private Invoker invoker;


    public AdminRoleHandlerStrategy(UserController userController) {

        this.userController = userController;
        invoker = new Invoker();

    }
    @Override
   public boolean processChoice(int choice, UserDTO loggedInUser, UtilityHandler ui,InputHandler inputHandler) throws SQLException {
        String EmailIpnut = null;
        String PasswordInput= null;
        String firstNameIpnut = null;
        String mobilePhoneInput = null;
        String addressIdInput = null;
        String roleIdInput = null;
        String statusInput = null;
        int userId = 0;

        UserManager userManager; // used in command design pattern
        switch (choice) {
            case 1:
                // Signup logic
                 EmailIpnut = inputHandler.getInputWithValidation("Enter email: ", "email");
                 PasswordInput= inputHandler.getInputWithValidation("Enter password: ", "password");
                 firstNameIpnut = inputHandler.getInputWithValidation("Enter first name: ", "text");
                 mobilePhoneInput = inputHandler.getInputWithValidation("Enter mobile phone: ", "phone");
                 addressIdInput = inputHandler.getInputWithValidation("Enter address ID: ", "addressid");
                 roleIdInput = inputHandler.getInputWithValidation("Are you An volunteer or a member chose 1 or 2 respectively: ", "role");
                 statusInput = inputHandler.getInputWithValidation("enter the status: ", "status");

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

                // command design pattern
                userManager = new UserManager();
                invoker.setCmd(new AddUserCmd(userManager, newUser));
                boolean isAdded = (boolean) invoker.executeCmd();
                //boolean isAdded = UserDAO.addUser(loggedInUser);//
                ui.showMessage("User added: " + isAdded);
                break;

            case 2:
                // Admin-specific logic for "Retrieve User by ID"
                // Retrieve User by ID
                String userIdInput = inputHandler.getInputWithValidation("enter userId : ", "userId");
                userId = Integer.parseInt(userIdInput);
                UserDTO retrievedUser;// = UserDAO.getUserById(userId);

                // command design pattern
                retrievedUser = new UserDTO(userId);
                userManager = new UserManager();
                invoker.setCmd(new RetrieveUserCmd(userManager, userId));
                retrievedUser = (UserDTO) invoker.executeCmd();

                if (retrievedUser != null) {
                    // Fetch Address and Role
                    AddressDTO address = AddressDAO.getAddressById(retrievedUser.getAddressId());
                    RoleDTO role = RoleDAO.getRoleById(retrievedUser.getRoleId());
//
                    ui.showMessage("User ID: " + retrievedUser.getId());
                    ui.showMessage("Name: " + retrievedUser.getFirstname());
                    ui.showMessage("Email: " + retrievedUser.getEmail());
                    ui.showMessage("Mobile Phone: " + retrievedUser.getMobilePhone());
                    ui.showMessage("Status: " + retrievedUser.getStatus());
                    ui.showMessage("Role: " + (role != null ? role.getName() : "Role not found"));
                    ui.showMessage("Address: " + (address != null ? address.getName() : "Address not found"));
                } else {
                    ui.showMessage("User not found.");
                }
                break;
            case 3:
                // Update User
                 userIdInput = inputHandler.getInputWithValidation("enter userId: ", "userId");
                userId = Integer.parseInt(userIdInput);

                // command design pattern
                userManager = new UserManager();
                invoker.setCmd(new RetrieveUserCmd(userManager, userId));
                UserDTO userToUpdate = (UserDTO) invoker.executeCmd();
                //UserDTO userToUpdate = UserDAO.getUserById(userId);

                if (userToUpdate != null) {


                     firstNameIpnut= inputHandler.getInputWithValidation("Enter name : ", "text");
                     userToUpdate.setFirstname(firstNameIpnut);
                     PasswordInput= inputHandler.getInputWithValidation("Enter password: ", "password");
                     userToUpdate.setPassword(PasswordInput);
                     EmailIpnut = inputHandler.getInputWithValidation("Enter email: ", "email");
                     userToUpdate.setEmail(EmailIpnut);
                     mobilePhoneInput = inputHandler.getInputWithValidation("Enter Mobile number: ", "phone");
                     userToUpdate.setMobilePhone(mobilePhoneInput);

                     statusInput = inputHandler.getInputWithValidation("Enter status : ", "status");
                     userToUpdate.setStatus(Integer.parseInt(statusInput));

                    // command design pattern
                    invoker.setCmd(new UpdateUserCmd(userManager, userToUpdate));
                    boolean isUpdated = (boolean) invoker.executeCmd();

//                    boolean isUpdated = UserDAO.updateUser(userToUpdate);
                    ui.showMessage("User updated: " + isUpdated);
                } else {
                    ui.showMessage("User not found.");//
                }
                break;
            case 4:
                // ANSI escape codes for colors
                  // Reset color

                // Admin-specific logic for "Retrieve All Users"
                // Retrieve All Users

                // command design pattern
                userManager = new UserManager();
                invoker.setCmd(new RetrieveAllUsersCmd(userManager));
                //List<UserDTO> users = UserDAO.getAllUsers();
                List<UserDTO> users = (List<UserDTO>) invoker.executeCmd();
                ui.showMessage("All users:");
                ui.showMessage("All users:");

// Print a header for the table with blue color//
                String format = "%-10s %-20s %-30s %-15s %-15s %-20s %-50s%n";
ui.displayTableHeader(format, "User ID", "Name", "Email", "Mobile Phone", "Status", "Role", "Address");
                
               

// Print a separator line//
                ui.showMessage("-------------------------------------------------------------------------------------------------------------------------------------------------");

                for (UserDTO user : users) {
                    // Fetch Address and Role for each user
                    String userAddress = AddressDAO.getFullAddressPath(user.getAddressId());
                    RoleDTO userRole = RoleDAO.getRoleById(user.getRoleId());
//
                    // Print each user's details in a formatted way
                     format = "%-10d %-20s %-30s %-15s %-15s %-20s %-50s%n";
ui.displayTableRow(
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
                ui.showMessage("**********************************************************");

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
                ui.showMessage("Logging out...");
                return true;

            case 12://
                ui.showMessage("Exiting...");
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
