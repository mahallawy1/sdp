/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.LoginStrategy;

import MODEL.DTO.User.UserDTO;

/**
 *
 *   
 * @author Eslam
 */
public class LoginService {
    
    LoginStrategy strategy ; // ref
    // Method to set the login strategy
    public void setStrategy(LoginStrategy strategy) {
        this.strategy = strategy;
    }
    public UserDTO executeLogin() {
        if (strategy == null) {
            throw new IllegalStateException("Login strategy not set");
        }
        return strategy.login();
    }
}
