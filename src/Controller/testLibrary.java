package Controller;

import MODEL.DAO.UserDAO;
import View.UserView;

// Main/Library.java
public class testLibrary {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        UserView userView = new UserView();
        UserController userController = new UserController(userDAO, userView);

        try {
            // Start by displaying the login menu
            userController.handleUserMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
