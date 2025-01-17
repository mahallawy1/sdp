package MODEL.Patterns.Command;

import java.sql.SQLException;

public interface ICmd<T> {
    public T execute() throws SQLException;
}
