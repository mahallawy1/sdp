package MODEL.Patterns.Command;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class LoginByPasswordCmd implements ICmd {
    public UserManager userManager;
    public String email;
    public String pswd;

    public LoginByPasswordCmd(UserManager userManager, String email, String pswd) {
        this.userManager = userManager;
        this.email = email;
        this.pswd = pswd;
    }

    @Override
    public Object execute() throws SQLException {
        return userManager.loginByPassword(email, pswd);
    }
}


