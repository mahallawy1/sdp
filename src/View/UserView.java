
package View;

import Controller.UserController;
import MODEL.DTO.User.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

import MODEL.Patterns.Observer.AObserver;
import MODEL.Patterns.Observer.DonationObserver;
import MODEL.Patterns.Observer.EventObserver;
import MODEL.Patterns.Observer.EventObserver4Volunteer;
import MODEL.Patterns.RoleHandlerStrategy.AdminRoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.MemberRoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.RoleHandlerStrategy;
import MODEL.Patterns.RoleHandlerStrategy.VolunteerRoleHandlerStrategy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import utils.InputValidator;

// View/UserView.java
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

    ////////////////////////////////////////
    /////////donation**************/*/*/*/*
    public boolean confirm(String message) {
        System.out.print(message);
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    public double getDonationAmount(String message) {
        System.out.print(message);
        return scanner.nextDouble();
    }

    public int getPaymentChoice() {
        System.out.println("Choose payment method:");
        System.out.println("1. Fawry Payment");
        System.out.println("2. Credit Card Payment");
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }

    ///////////////////////////////////////
    public void showMessage(String message) {
        System.out.println(message);
    }


    // Method to handle the user’s menu choice

    public void showNotification(AObserver obs) {
        if (obs.isNewNotification()) {
            if (obs instanceof EventObserver4Volunteer) {
                System.out.println("\ud83d\udd14  New Event Added! \n Event Name: " +
                        ((EventObserver4Volunteer) obs).getEventName() + "\n Description: " +
                        ((EventObserver4Volunteer) obs).getDescription() + ".\nSee you \ud83d\ude04.");
            } else if (obs instanceof EventObserver) {
                System.out.println("\uD83D\uDD14  New Event Added! " +
                        ((EventObserver) obs).getEventName() + " at " + ((EventObserver) obs).getEventDate() +
                        " from " + ((EventObserver) obs).getTimeFrom() + " to " + ((EventObserver) obs).getTimeTo() +
                        ". See you \uD83D\uDE04.");

            } else if (obs instanceof DonationObserver) {
                System.out.println("\uD83D\uDD14  New Donation \uD83C\uDF89. " +
                        ((DonationObserver) obs).getDonationAmount() + "EG£ from " +
                        ((DonationObserver) obs).getDonorName() + ".");
            }
            obs.clearNotification();
        }
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

            // Process the user's choice based on the role handler
            exit = roleHandler.processChoice(choice, loggedInUser, this);

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
                return new AdminRoleHandlerStrategy(userController);
            case 2:
                return new VolunteerRoleHandlerStrategy(userController);
            case 3:
                return new MemberRoleHandlerStrategy(userController);
            default:
                return null;
        }
    }

//////////////////////////////////////////

    public String getEventName() {
        System.out.println("Enter Event Name:");
        return scanner.nextLine();
    }

    public int getEventTypeId(int userRoleId) {
        int eventTypeId;
        if (userRoleId == 2) {  // Volunteer
            eventTypeId = 0; // Default to "seminar" for volunteer
        } else {
            System.out.println("Enter Event Type ID:");
            eventTypeId = Integer.parseInt(scanner.nextLine());
        }
        return eventTypeId;
    }

    public String getEventDescription() {
        System.out.println("Enter Event Description:");
        return scanner.nextLine();
    }

    public LocalDate getEventDate() {
        LocalDate eventDate;
        while (true) {
            System.out.println("Enter Event Date (yyyy-mm-dd):");
            try {
                eventDate = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                showMessage("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
        return eventDate;
    }

    public LocalTime getStartTime() {
        LocalTime startTime;
        while (true) {
            System.out.println("Enter Start Time (HH:mm):");
            try {
                startTime = LocalTime.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                showMessage("Invalid time format. Please use HH:mm.");
            }
        }
        return startTime;
    }

    public LocalTime getEndTime() {
        LocalTime endTime;
        while (true) {
            System.out.println("Enter End Time (HH:mm):");
            try {
                endTime = LocalTime.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                showMessage("Invalid time format. Please use HH:mm.");
            }
        }
        return endTime;
    }

    public ArrayList<Integer> getSkills() {
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter skill ids (type 'done' to finish):");
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt()); // Add integer to ArrayList
            } else {
                String input = scanner.next();
                if (input.equalsIgnoreCase("done")) {
                    break; // Exit loop if user types "done"
                } else {
                    System.out.println("Invalid input. Please enter an integer or type 'done'.");
                }
            }
        }

        return list;
    }

    public int getEventIdForDeletion() {
        System.out.println("Enter the Event ID to delete:");
        return Integer.parseInt(scanner.nextLine());
    }

    //////////////////////////////////
    public int getUsrIdForDeletion() {
        System.out.println("Enter the usr ID to delete:");
        return Integer.parseInt(scanner.nextLine());
    }
    //////////////////////////

    public void displayMessageWithId(String message, int id) {
        System.out.println(message + " with ID: " + id);
    }

    public void displayTableHeader(String format, String... headers) {
        String BLUE = "\033[34m";
        String RESET = "\033[0m";

        System.out.printf(BLUE + format + RESET, (Object[]) headers);
    }

    public void displayTableRow(String format, Object... values) {
        System.out.printf(format, values);
    }

    //////////////////////////////////
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
