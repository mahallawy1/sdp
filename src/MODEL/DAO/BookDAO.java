package MODEL.DAO;

import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Method to get all books from the database
    public List<BookDTO> getAllBooks() throws SQLException {
        List<BookDTO> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE deleted = false";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookDTO book = mapRowToBook(rs);
                books.add(book);
            }
        }
        return books;
    }

    // Method to get a book by its ID
    public BookDTO getBookById(int id) throws SQLException {
        String query = "SELECT * FROM books WHERE id = ? AND deleted = false";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBook(rs);
                }
            }
        }
        return null;
    }

    // Method to add a new book and generate keys
    public void addBook(BookDTO book) throws SQLException {
        String query = "INSERT INTO books (description, title, cover, deleted, publish_year, quantity, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, book.getDescription());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getCover());
            stmt.setBoolean(4, book.getDeleted());
            stmt.setInt(5, book.getPublishYear());
            stmt.setInt(6, book.getQuantity());
            stmt.setString(7, book.getStatus());

            stmt.executeUpdate();

            // Retrieve and set the generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve generated key for book.");
                }
            }
        }
    }

    // Method to update an existing book
    public void updateBook(BookDTO book) throws SQLException {
        String query = "UPDATE books SET description = ?, title = ?, cover = ?, deleted = ?, publish_year = ?, quantity = ?, status = ? WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getDescription());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getCover());
            stmt.setBoolean(4, book.getDeleted());
            stmt.setInt(5, book.getPublishYear());
            stmt.setInt(6, book.getQuantity());
            stmt.setString(7, book.getStatus());
            stmt.setInt(8, book.getId());

            stmt.executeUpdate();
        }
    }

    // Method to delete a book (soft delete by setting `deleted` to true)
    public void deleteBook(int id) throws SQLException {
        String query = "UPDATE books SET deleted = true WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a BookDTO object
    private BookDTO mapRowToBook(ResultSet rs) throws SQLException {
        BookDTO book = new BookDTO();
        book.setId(rs.getInt("id"));
        book.setDescription(rs.getString("description"));
        book.setTitle(rs.getString("title"));
        book.setCover(rs.getString("cover"));
        book.setDeleted(rs.getBoolean("deleted"));
        book.setPublishYear(rs.getInt("publish_year"));
        book.setQuantity(rs.getInt("quantity"));
        book.setStatus(rs.getString("status"));
        return book;
    }
}