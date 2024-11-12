package Controller;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;
import View.UserView;

import java.sql.SQLException;

// Controller/UserController.java
public class UserController {
    private UserDAO userDAO;
    private UserView userView;

    public UserController(UserDAO userDAO, UserView userView) {
        this.userDAO = userDAO;
        this.userView = userView;
    }

    public void handleUserMenu() throws SQLException {
        userView.showLoginMenu();
        int choice = userView.getChoice();

        switch (choice) {
            case 1:
                loginUser(); // Call login method
                break;
            case 2:
                signupUser(); // Call signup method
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);  // Exit the application
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


    private void loginUser() throws SQLException {
        userView.showMessage("Choose login method:");
        userView.showMessage("1. Login by Email and Password");
        userView.showMessage("2. Login by Mobile Phone");

        int loginChoice = userView.getChoice();
        UserDTO loggedInUser = null;

        switch (loginChoice) {
            case 1:
                // Login by Email and Password
                String email = userView.getInputWithValidation("Enter email: " , "email");
                String password = userView.getInputWithValidation("Enter password: " , "password");
                loggedInUser = userDAO.getUserByEmailAndPassword(email, password);
                break;
            case 2:
                // Login by Mobile Phone
                String mobilePhone = userView.getInputWithValidation("Enter mobile phone: " , "phone");
                loggedInUser = userDAO.getUserByMobilePhone(mobilePhone);
                break;

            default:
                userView.showMessage("Invalid login choice.");
                return; // Exit the method on invalid choice
        }

        if (loggedInUser != null) {
            userView.showMessage("Login successful! Welcome, " + loggedInUser.getFirstname());
            userView.showMainMenu(loggedInUser); // Show menu after successful login
        } else {
            userView.showMessage("Login failed. Please check your credentials.");
        }
    }


    private void signupUser() {
        // Signup logic
        String signupEmail = userView.getInputWithValidation("Enter email: ", "email");
        String signupPassword = userView.getInputWithValidation("Enter password: ", "password");
        String firstName = userView.getInputWithValidation("Enter first name: ", "text");
        String mobilePhone = userView.getInputWithValidation("Enter mobile phone: ", "phone");
        String addressIdInput = userView.getInputWithValidation("Enter address ID: ", "addressid");
        String roleIdInput = userView.getInputWithValidation("Are you An author or a donor chose 1 or 2 respectively: ", "role");
        // what is the status i don't know !!
        String statusInput = userView.getInputWithValidation("enter the status: ", "status");

        UserDTO newUser = new UserDTO();
        // Set user data
        newUser.setEmail(signupEmail);
        newUser.setPassword(signupPassword);
        newUser.setFirstname(firstName);
        newUser.setMobilePhone(mobilePhone);


        try {
            // Converting addressId and roleId from String to Integer
            int addressId = Integer.parseInt(addressIdInput);
            int roleId = Integer.parseInt(roleIdInput);
            int status = Integer.parseInt(statusInput);
            // increment by 1 because in the db 1 is the admin , 2 is the author and 3 is the donor !!
            // no admin account should be created here
            roleId++;
            // Set these values in the UserDTO
            newUser.setAddressId(addressId);
            newUser.setRoleId(roleId);
            newUser.setStatus(status);



            // Add the user to the database
            boolean isAdded = userDAO.addUser(newUser);

            // Provide feedback to the user
            if (isAdded) {
                userView.showMessage("Signup successful!");
                userView.showMainMenu(newUser);  // Show the main menu after successful signup
            } else {
                userView.showMessage("Signup failed. Please try again.");
            }
        } catch (NumberFormatException | SQLException e) {
            userView.showMessage("Invalid input format for address ID, role ID, or status. Please ensure they are correct.");
        }
    }

    public void handleMainMenu(UserDTO loggedInUser) throws SQLException {
        userView.showMainMenu(loggedInUser);
        // Handle each menu item
    }

    // Other controller methods for handling user actions (add donation, create event, etc.)
}
