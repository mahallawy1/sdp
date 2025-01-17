package MODEL.Patterns.Command.Cmd;

import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class UpdateUserCmd implements ICmd {
    public UserManager userManager;
    public UserDTO user;
    public UpdateUserCmd(UserManager userManager, UserDTO user) {
        this.userManager = userManager;
        this.user = user;
    }
    @Override
    public Object execute() throws SQLException {
        return userManager.updateUser(user);
    }
}
