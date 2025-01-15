package MODEL.Patterns.Command.Cmd;

import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.VolunteringManager;

import java.sql.SQLException;

public class JoinEvent2VolunteerCmd implements ICommand {
    public VolunteringManager volunteringManager;

    public JoinEvent2VolunteerCmd(VolunteringManager volunteringManager) {
        this.volunteringManager = volunteringManager;
    }

    @Override
    public void execute() throws SQLException {
        volunteringManager.joinEvent2Volunteer();
    }
}
