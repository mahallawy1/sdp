/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author hussien
 */


import Controller.UserController;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.RoleHandlerStrategy.AdminRoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.MemberRoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.RoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.VolunteerRoleHandlerStrategy;
import java.sql.SQLException;
import java.util.Scanner;

public class UserView {
     private Scanner scanner;
    private UserController userController;  // Add this line

    public UserView(UserController userController) {
        this.scanner = new Scanner(System.in);
        this.userController = userController;  // Store the controller instance
    }

    public void showLoginMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Exit");
    }
    public int getUsrIdForDeletion() {
      System.out.println("Enter the usr ID to delete:");
      return Integer.parseInt(scanner.nextLine());
    }
 public void showMainMenu(UserDTO loggedInUser) throws SQLException {
        RoleHandlerStrategy roleHandler = getRoleHandler(loggedInUser.getRoleId());

        if (roleHandler == null) {
            System.out.println("Invalid role ID. Access denied.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            // Display the menu
            displayMenu(loggedInUser);

            // Get the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            InputHandler inputHandler = new InputHandler();
            UtilityHandler utilityHandler = new UtilityHandler();
            // Process the user's choice based on the role handler
            exit = roleHandler.processChoice(choice, loggedInUser,utilityHandler  ,inputHandler);

            // If the choice was to exit (logout), break out of the loop and show login menu
            if (exit) {
                userController.handleUserMenu();
            }
        }
    }

    private void displayMenu(UserDTO loggedInUser) {

        // ANSI escape codes for colors
        String BLUE = "\033[34m";   // Blue
        String GREEN = "\033[32m";  // Green
        String RESET = "\033[0m";   // Reset color

// Emojis
        String sunglassesEmoji = "\uD83D\uDE0E";  // Unicode for 😎 (smiling face with sunglasses)
        String glowingStarEmoji = "\uD83C\uDF1F";  // Unicode for 🌟 (glowing star)

// Print the message with colors and emojis
        System.out.println("WELCOME TO YOUR" + BLUE + " CONTROL PANEL, " + GREEN + loggedInUser.getFirstname() + " " + sunglassesEmoji + " " + glowingStarEmoji + RESET);
        if (loggedInUser.getRoleId() == 1) {
            System.out.println("1. Add User");
            System.out.println("2. Retrieve User by ID");
            System.out.println("3. Update User");
            System.out.println("4. Retrieve All Users");
            System.out.println("5. Delete User by ID");
            System.out.println("6. Add Donation");
            System.out.println("7. Create Event");
            System.out.println("8. Delete Event");
            System.out.println("9. Add a Book");
            System.out.println("10. Delete a Book");
            System.out.println("11. Logout");
            System.out.println("12. Exit");
        } else if (loggedInUser.getRoleId() == 2) {
            System.out.println("1. Add Donation");
            System.out.println("2. Create Event");
            System.out.println("3. Delete Event");
            System.out.println("4. join Event");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
        } else if (loggedInUser.getRoleId() == 3) {
            System.out.println("1. Add Donation");
            System.out.println("2. Display Books");
            System.out.println("3. Borrow A book");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
        }
    }

    private RoleHandlerStrategy getRoleHandler(int roleId) {
        switch (roleId) {
            case 1:
                return new AdminRoleHandlerStrategy(userController);
            case 2:
                return new VolunteerRoleHandlerStrategy(userController);
            case 3:
                return new MemberRoleHandlerStrategy(userController);
            default:
                return null;
        }
    }
}
