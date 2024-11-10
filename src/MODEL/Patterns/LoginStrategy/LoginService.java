/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.LoginStrategy;

/**
 *
 * @author Eslam
 */
public class LoginService {
    
    LoginStrategy strategy ; // ref
    // Method to set the login strategy
    public void setStrategy(LoginStrategy strategy) {
        this.strategy = strategy;
    }
}
