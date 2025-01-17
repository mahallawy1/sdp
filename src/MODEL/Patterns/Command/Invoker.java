package MODEL.Patterns.Command;

import java.sql.SQLException;

public class Invoker {

    ICmd<?> cmd;



    public void setCmd(ICmd cmd) {this.cmd = cmd;}

    public Object executeCmd() throws SQLException {
        return cmd.execute();
    }
}
