package MODEL.Patterns.Command.Manager;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.LoginStrategy.EmailPasswordLoginStrategy;
import MODEL.Patterns.LoginStrategy.LoginService;
import MODEL.Patterns.LoginStrategy.MobilePhoneLoginStrategy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private  UserDTO user;

    public UserManager() {}


    public UserDTO loginByPassword(String email, String pswd) throws SQLException {
        UserDAO userDAO = new UserDAO();
        LoginService loginService = new LoginService();
        loginService.setStrategy(new EmailPasswordLoginStrategy(email, pswd, userDAO));
        return loginService.executeLogin();
    }

    public Object loginByMobilePhone(String phone) throws SQLException {
        UserDAO userDAO = new UserDAO();
        LoginService loginService = new LoginService();
        loginService.setStrategy(new MobilePhoneLoginStrategy(phone, userDAO));
        return loginService.executeLogin();
    }

    public boolean addUser(UserDTO user) throws SQLException {
        UserDAO userDAO = new UserDAO();
        return userDAO.addUser(user);
    }

    public UserDTO retrieveUser(int id) throws  SQLException {
        return UserDAO.getUserById(id);
    }

    public boolean updateUser(UserDTO user) throws  SQLException {
        return UserDAO.updateUser(user);
    }

    public boolean deleteUser(int id) throws  SQLException {
        return UserDAO.deleteUser(id);
    }

    public ArrayList<UserDTO> retrieveAllUsers() throws  SQLException {
        return UserDAO.getAllUsers();//
    }



}
