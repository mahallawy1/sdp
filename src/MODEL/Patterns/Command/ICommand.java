package MODEL.Patterns.Command;

import java.sql.SQLException;

public interface ICommand {
    public void execute() throws SQLException;
}
