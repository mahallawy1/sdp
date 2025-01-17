package MODEL.Patterns.Command.Manager;

import MODEL.DAO.BookDAO;
import MODEL.DAO.UserDAO;
import MODEL.DTO.Book.BookDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Iterator.AvailableBookCollection;


import java.sql.SQLException;
import java.util.List;

public class BookManager {

    private BookDTO book;
    private boolean isSuccessful;
    private AvailableBookCollection books;

    public BookManager() {}


    public BookManager(BookDTO book) {
        this.book = book;
    }


    public void setUser(BookDTO book) {
        this.book = book;
    }

    public BookDTO getBook() {
        return book;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public AvailableBookCollection getBooks() {
        return books;
    }

    public void addBook() {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.addBook(book);
            isSuccessful = true;
        } catch (SQLException e) {
            isSuccessful = false;
        }
    }

    public void deleteBook() throws SQLException {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.deleteBook(book.getId());
            isSuccessful = true;
        } catch (SQLException e) {
            isSuccessful = false;
            throw(e);
        }
    }

    public void retrieveAllBooks() throws  SQLException {
        BookDAO bookDAO = new BookDAO();
        books =  bookDAO.getAllBooks();

    }


}
