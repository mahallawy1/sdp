package MODEL.Patterns.Command;

import java.sql.SQLException;

public class Invoker {
    ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void execute() throws SQLException {
        command.execute();
    }
}
