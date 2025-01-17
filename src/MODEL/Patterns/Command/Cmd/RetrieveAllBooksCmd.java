package MODEL.Patterns.Command.Cmd;

import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.BookManager;

import java.sql.SQLException;

public class RetrieveAllBooksCmd implements ICmd {public BookManager bookManager;

    public RetrieveAllBooksCmd(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @Override
    public Object execute() throws SQLException {
        return bookManager.retrieveAllBooks();
    }
}
