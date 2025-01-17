package MODEL.Patterns.Command;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class LoginByMobilePhoneCmd implements ICmd {
    public UserManager userManager;
    public String phone;

    public LoginByMobilePhoneCmd(UserManager userManager, String phone) {

        this.userManager = userManager;
        this.phone = phone;
    }

    @Override
    public Object execute() throws SQLException {
        return userManager.loginByMobilePhone(phone);
    }
}
