package MODEL.Patterns.Command.Cmd;

import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.EventManager;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class DeleteEventCmd implements ICommand {
    public EventManager eventManager;
    public DeleteEventCmd(EventManager eventManager) {
        this.eventManager = eventManager;
    }
    @Override
    public void execute() throws SQLException {
        eventManager.deleteEvent();
    }
}
