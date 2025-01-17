package MODEL.Patterns.Command.Manager;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.LoginStrategy.EmailPasswordLoginStrategy;
import MODEL.Patterns.LoginStrategy.LoginService;
import MODEL.Patterns.LoginStrategy.MobilePhoneLoginStrategy;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

    private  UserDTO user;
    private boolean isSuccessful;
    private List<UserDTO> users;

    public UserManager() {}
    public UserManager(UserDTO user) {
        this.user = user;
    }

//    public UserReciever(UserDTO user) {
//        ArrayList<UserDTO> arrList = new ArrayList<UserDTO>();
//        arrList.add(user);
//        this.users = arrList;
//    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return  user;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void loginByPassword() throws SQLException {
        UserDAO userDAO = new UserDAO();
        LoginService loginService = new LoginService();
//        UserDTO user = users.get(0);
        loginService.setStrategy(new EmailPasswordLoginStrategy(user.getEmail(), user.getPassword(), userDAO));
        this.user = loginService.executeLogin();
//        this.users.set(0, loginService.executeLogin());
    }

    public void loginByMobilePhone() throws SQLException {
        UserDAO userDAO = new UserDAO();
        LoginService loginService = new LoginService();
        loginService.setStrategy(new MobilePhoneLoginStrategy(user.getMobilePhone(), userDAO));
        this.user = loginService.executeLogin();
    }

    public void addUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        boolean isAdded = userDAO.addUser(user);
        if(!isAdded) {
            user = null;
            isSuccessful = false;
        } else
            isSuccessful = true;
    }

    public void retrieveUser() throws  SQLException {
        UserDAO userDAO = new UserDAO();
        user = UserDAO.getUserById(user.getId());
    }

    public void updateUser() throws  SQLException {
        UserDAO userDAO = new UserDAO();
        isSuccessful = UserDAO.updateUser(user)? true : false;
    }

    public void deleteUser() throws  SQLException {
        UserDAO userDAO = new UserDAO();
        isSuccessful = UserDAO.deleteUser(user.getId())? true : false;
    }

    public void retrieveAllUsers() throws  SQLException {
        users = UserDAO.getAllUsers();//

    }





//    public void retrieveUser() throws SQLException {
//        this.users.set(0,UserDAO.getUserById((users.get(0)).getId()));
//    }
//
//    public void addUser() throws SQLException {
//        isSuccessful = UserDAO.addUser(users.get(0));//
//    }




}
