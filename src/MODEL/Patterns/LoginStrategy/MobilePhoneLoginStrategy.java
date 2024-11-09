/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.LoginStrategy;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eslam
 */
public class MobilePhoneLoginStrategy implements LoginStrategy {

    private final String mobilePhone;
    private final UserDAO userDAO;

    public MobilePhoneLoginStrategy(String mobilePhone, UserDAO userDAO) {
        this.mobilePhone = mobilePhone;
        this.userDAO = userDAO;
    }

    @Override
    public UserDTO login() {
        try {
            // Implement login logic using mobile phone
            return userDAO.getUserByMobilePhone(mobilePhone); // note : should complete the implementation in userDAO
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneLoginStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
