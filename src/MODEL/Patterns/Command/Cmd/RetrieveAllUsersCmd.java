package MODEL.Patterns.Command;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class RetrieveAllUsersCmd implements ICmd {
    public UserManager userManager;
    public RetrieveAllUsersCmd(UserManager userManager) {
        this.userManager = userManager;
    }
    @Override
    public Object execute() throws SQLException {
        return userManager.retrieveAllUsers();
    }
}
