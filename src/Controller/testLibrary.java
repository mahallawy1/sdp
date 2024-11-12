package Controller;

import MODEL.DAO.UserDAO;
import View.UserView;

// Main/Library.java
public class testLibrary {
    public static void main(String[] args) {
        // Instantiate UserDAO
        UserDAO userDAO = new UserDAO();

        // Create the UserController and pass the UserDAO to it
        UserController userController = new UserController(userDAO, null);  // Temporarily passing null for UserView

        // Now create UserView, but pass the userController
        UserView userView = new UserView(userController);

        // Set the UserView instance in the UserController
        userController.setUserView(userView);  // Assuming you have a setter method in UserController

        try {
            // Start by displaying the login menu
            userController.handleUserMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
