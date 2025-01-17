package MODEL.Patterns.Command.Manager;

import MODEL.DAO.BookDAO;
import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.AvailableBookCollection;


import java.sql.SQLException;

public class BookManager {

    public BookManager() {}


    public boolean addBook(BookDTO book) {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.addBook(book);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteBook(int id) throws SQLException {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.deleteBook(id);
            return true;
        } catch (SQLException e) {
            throw(e);
//            return false;
        }
    }

    public AvailableBookCollection retrieveAllBooks() throws  SQLException {
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getAllBooks();

    }


}
