package utils;

public class InputValidator {

    // Validate email format
    public static boolean isValidEmail(String email) {
        // Simple regex for basic email validation
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    // Validate password (simple check: length >= 6)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    // Validate phone number (simple check: should only contain digits and be of a reasonable length)
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    // Validate address ID (should be a positive integer)
    public static boolean isValidAddressId(String addressId) {
        try {
            int id = Integer.parseInt(addressId);
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate role ID (simple check: should be an integer, for example, 1 for user and 2 for admin)
    public static boolean isValidRoleId(String roleId) {
        try {
            int id = Integer.parseInt(roleId);
            return id == 1 || id == 2;  // Example check: 1 for user, 2 for admin
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate status (true/false)
    public static boolean isValidStatus(String status) {
        return "true".equalsIgnoreCase(status) || "false".equalsIgnoreCase(status) || "1".equals(status) || "0".equals(status);
    }


    public static boolean isValidUserId(String input) {
        try {
            int userId = Integer.parseInt(input);
            return userId > 0;  // Ensure user ID is greater than 0
        } catch (NumberFormatException e) {
            return false;  // Invalid input if it's not an integer
        }
    }



}
