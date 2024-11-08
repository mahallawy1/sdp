/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.LoginStrategy;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;

/**
 *
 * @author Eslam
 */
public class EmailPasswordLoginStrategy implements ILogin {
    private final String email;
    private final String password;
    private final UserDAO userDAO;

    public EmailPasswordLoginStrategy(String email, String password, UserDAO userDAO) {
        this.email = email;
        this.password = password;
        this.userDAO = userDAO;
    }
    @Override
    public UserDTO login() {
        return userDAO.getUserByEmailAndPassword(email, password);// note : should complete the implementation in userDAO
    }
    
}
