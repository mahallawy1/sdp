package MODEL.Patterns.Command.Cmd;

import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class AddUserCmd implements ICommand {
    public UserManager userManager;

    public AddUserCmd(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void execute() throws SQLException {
        userManager.addUser();
    }
}


