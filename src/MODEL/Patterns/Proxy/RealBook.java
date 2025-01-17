package MODEL.Patterns.Proxy;

import MODEL.DAO.BookDAO;
import MODEL.DTO.Book.BookDTO;
import java.sql.SQLException;

// Real Book class
public class RealBook implements IBook {
    private String title;
    private Integer publishYear;
    private String description;
    private Integer quantity;
    private Integer bookID;
    private BookDAO bookDAO;

    // Constructor that initializes the RealBook
    public RealBook(String title, Integer publishYear, String description, Integer quantity, Integer bookID) throws SQLException {
        this.title = title;
        this.publishYear = publishYear;
        this.description = description;
        this.quantity = quantity;
        this.bookID = bookID;
        bookDAO = new BookDAO();
        
        // Simulating heavy loading
        loadFromDatabase();
    }

    // Method to load the book from the database using the bookID
    private void loadFromDatabase() throws SQLException {
        BookDTO bookDTO = this.bookDAO.getBookById(this.bookID);
        
        // Assuming bookDTO contains the relevant fields
        this.title = bookDTO.getTitle();
        this.publishYear = bookDTO.getPublishYear();
        this.description = bookDTO.getDescription();
        this.quantity = bookDTO.getQuantity();
    }
    
    @Override
    public void display() {
        System.out.println("Book: " + title + ", Published Year: " + publishYear + ", Description: " + description + ", Quantity: " + quantity);
    }
    
    @Override
    public void update(String title, Integer publishYear, String description, Integer quantity) {
        this.title = title;
        this.publishYear = publishYear;
        this.description = description;
        this.quantity = quantity;
    }

   
}