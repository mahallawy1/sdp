package MODEL.Patterns.Command.Cmd;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.BookManager;

import java.sql.SQLException;

public class DeleteBookCmd implements ICmd {
    public BookManager bookManager;
    public int id;

    public DeleteBookCmd(BookManager bookManager, int id) {
        this.bookManager = bookManager;
        this.id = id;
    }

    @Override
    public Object execute() throws SQLException {
        return bookManager.deleteBook(id);
    }
}
