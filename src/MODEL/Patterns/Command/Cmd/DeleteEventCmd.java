package MODEL.Patterns.Command;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.EventManager;
import MODEL.Patterns.Command.Manager.UserManager;

import java.sql.SQLException;

public class DeleteEventCmd implements ICmd {
    public EventManager eventManager;
    public int id;
    public DeleteEventCmd(EventManager eventManager, int id) {
        this.eventManager = eventManager;
        this.id = id;
    }
    @Override
    public Object execute() throws SQLException {
        return eventManager.deleteEvent(id);
    }
}
