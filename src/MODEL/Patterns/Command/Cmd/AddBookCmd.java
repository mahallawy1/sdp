package MODEL.Patterns.Command.Cmd;

import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.BookManager;

import java.sql.SQLException;

public class AddBookCmd implements ICommand {
    public BookManager bookManager;

    public AddBookCmd(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @Override
    public void execute() throws SQLException {
        bookManager.addBook();
    }
}
