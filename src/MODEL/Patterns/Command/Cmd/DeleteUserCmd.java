package MODEL.Patterns.Command;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class DeleteUserCmd implements ICmd {
    public UserManager userManager;
    public int id;
    public DeleteUserCmd(UserManager userManager, int id) {

        this.userManager = userManager;
        this.id = id;
    }
    @Override
    public Object execute() throws SQLException {
        return userManager.deleteUser(id);
    }
}
