
package View;
import MODEL.DTO.User.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

import MODEL.Patterns.RoleHandlerStrategy.AdminRoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.MemberRoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.RoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.VolunteerRoleHandlerStrategy;
import utils.InputValidator;
// View/UserView.java
public class UserView {
    private Scanner scanner;

    public UserView() {
        scanner = new Scanner(System.in);
    }


    public void showLoginMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Exit");
    }
    public int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consumes the leftover newline character
        return choice;
    }


    public String getInput(String prompt) {
        System.out.print(prompt);
        String answer = scanner.nextLine();
        return answer;
    }


    public void showMessage(String message) {
        System.out.println(message);
    }




    // Method to handle the user’s menu choice


        public void showMainMenu(UserDTO loggedInUser) throws SQLException {
            RoleHandlerStrategy roleHandler = getRoleHandler(loggedInUser.getRoleId());

            if (roleHandler == null) {
                System.out.println("Invalid role ID. Access denied.");
                return;
            }

            // Display menu based on the user's role
            displayMenu(loggedInUser);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            // this mean the current user view is passed as parameter !!
            roleHandler.processChoice(choice, loggedInUser ,  this);
        }

        private void displayMenu(UserDTO loggedInUser) {
            System.out.println("WELCOME TO YOUR CONTROL PANEL, " + loggedInUser.getFirstname());
            if (loggedInUser.getRoleId() == 1) {
                System.out.println("1. Add User");
                System.out.println("2. Retrieve User by ID");
                System.out.println("3. Update User");
                System.out.println("4. Retrieve All Users");
                System.out.println("5. Delete User by ID");
                System.out.println("6. Add Donation");
                System.out.println("7. Create Event");
                System.out.println("8. Delete Event");
                System.out.println("9. Logout");
                System.out.println("10. Exit");
            } else if (loggedInUser.getRoleId() == 2) {
                System.out.println("1. Add Donation");
                System.out.println("2. Create Event");
                System.out.println("3. Delete Event");
                System.out.println("4. Logout");
                System.out.println("5. Exit");
            } else if (loggedInUser.getRoleId() == 3) {
                System.out.println("1. Add Donation");
                System.out.println("2. Logout");
                System.out.println("3. Exit");
            }
        }

        private RoleHandlerStrategy getRoleHandler(int roleId) {
            switch (roleId) {
                case 1:
                    return new AdminRoleHandlerStrategy();
                case 2:
                    return new VolunteerRoleHandlerStrategy();
                case 3:
                    return new MemberRoleHandlerStrategy();
                default:
                    return null;
            }
        }


    public String getInputWithValidation(String prompt, String validationType) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            input = scanner.nextLine();

            // Check if the user wants to return to the login menu
            if (input.equalsIgnoreCase("back")) {
                // You can implement logic here to return to the login menu
                showMessage("Returning to the login menu...");
                return null; // Return null or a flag value to indicate returning
            }

            // Validate input based on the validation type
            switch (validationType.toLowerCase()) {

                case "userid":
                    if (InputValidator.isValidUserId(input)) {
                        isValid = true;
                    } else {
                        showMessage("Enter A valid userID > 0 ");
                    }
                    break;
                case "email":
                    if (InputValidator.isValidEmail(input)) {
                        isValid = true;
                    } else {
                        showMessage("Invalid email format. Please enter a valid email address.");
                    }
                    break;

                case "password":
                    if (InputValidator.isValidPassword(input)) {
                        isValid = true;
                    } else {
                        showMessage("Password must be at least 6 characters long.");
                    }
                    break;

                case "phone":
                    if (InputValidator.isValidPhoneNumber(input)) {
                        isValid = true;
                    } else {
                        showMessage("Phone number must contain exactly 10 digits.");
                    }
                    break;

                case "addressid":
                    if (InputValidator.isValidAddressId(input)) {
                        isValid = true;
                    } else {
                        showMessage("Address ID must be a valid integer.");
                    }
                    break;

                case "role":
                    if (InputValidator.isValidRoleId(input)) {
                        isValid = true;
                    } else {
                        showMessage("Role ID must be a valid integer and you can pick either 1 or 2 !!!");
                    }
                    break;

                case "status":
                    if (InputValidator.isValidStatus(input)) {
                        isValid = true;
                    } else {
                        showMessage("Status must be 'true' or 'false' or 1 or 2");
                    }
                    break;
                default:
                    isValid = true;  // No validation required
                    break;
            }
        }

        return input; // Return the valid input
    }


}
