/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author hussien
 */


import java.util.Scanner;
import utils.InputValidator;

public class InputHandler {
    private Scanner scanner;
  
    
  
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }
    
    public int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consumes the leftover newline character
        return choice;
    }



    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public double getDoubleInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
    
        public String getInputWithValidation(String prompt, String validationType) {
        String input = "";
        boolean isValid = false;
        UtilityHandler UI = new UtilityHandler();
        while (!isValid) {
            System.out.print(prompt);
            input = scanner.nextLine();

            // Check if the user wants to return to the login menu
            if (input.equalsIgnoreCase("back")) {
                // You can implement logic here to return to the login menu
                UI.showMessage("Returning to the login menu...");
                return null; // Return null or a flag value to indicate returning
            }

            // Validate input based on the validation type
            switch (validationType.toLowerCase()) {
                
                case "userid":
                    if (InputValidator.isValidUserId(input)) {
                        isValid = true;
                    } else {
                       UI.showMessage("Enter A valid userID > 0 ");
                    }
                    break;
                case "email":
                    if (InputValidator.isValidEmail(input)) {
                        isValid = true;
                    } else {
                        UI.showMessage("Invalid email format. Please enter a valid email address.");
                    }
                    break;

                case "password":
                    if (InputValidator.isValidPassword(input)) {
                        isValid = true;
                    } else {
                        UI.showMessage("Password must be at least 6 characters long.");
                    }
                    break;

                case "phone":
                    if (InputValidator.isValidPhoneNumber(input)) {
                        isValid = true;
                    } else {
                      UI.showMessage("Phone number must contain exactly 10 digits.");
                    }
                    break;

                case "addressid":
                    if (InputValidator.isValidAddressId(input)) {
                        isValid = true;
                    } else {
                       UI.showMessage("Address ID must be a valid integer.");
                    }
                    break;

                case "role":
                    if (InputValidator.isValidRoleId(input)) {
                        isValid = true;
                    } else {
                       UI.showMessage("Role ID must be a valid integer and you can pick either 1 or 2 !!!");
                    }
                    break;

                case "status":
                    if (InputValidator.isValidStatus(input)) {
                        isValid = true;
                    } else {
                       UI.showMessage("Status must be 'true' or 'false' or 1 or 2");
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
