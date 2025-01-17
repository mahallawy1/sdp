package MODEL.Patterns.Command;

import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class AddUserCmd implements ICmd {
    public UserManager userManager;
    public UserDTO user;

    public AddUserCmd(UserManager userManager, UserDTO user) {
        this.userManager = userManager;
        this.user = user;
    }

    @Override
    public Object execute() throws SQLException {
        return userManager.addUser(user);
    }
}


