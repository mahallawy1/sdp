package MODEL.Patterns.Command;

import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.Manager.BookManager;

import java.sql.SQLException;

public class AddBookCmd implements ICmd {
    public BookManager bookManager;
    public BookDTO bookDTO;


    public AddBookCmd(BookManager bookManager, BookDTO bookDTO) {
        this.bookManager = bookManager;
        this.bookDTO = bookDTO;
    }

    @Override
    public Object execute() throws SQLException {
        return bookManager.addBook(bookDTO);
    }
}
