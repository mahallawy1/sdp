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
public class MobilePhoneLoginStrategy implements ILogin {

    private final String mobilePhone;
    private final UserDAO userDAO;

    public MobilePhoneLoginStrategy(String mobilePhone, UserDAO userDAO) {
        this.mobilePhone = mobilePhone;
        this.userDAO = userDAO;
    }

    @Override
    public UserDTO login() {
        // Implement login logic using mobile phone
        return userDAO.getUserByMobilePhone(mobilePhone); // note : should complete the implementation in userDAO
    }
    
}
