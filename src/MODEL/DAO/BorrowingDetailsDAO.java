/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DAO;

/**
 *
 * @author hussien
 */


import MODEL.DTO.Book.BorrowDetailsDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDetailsDAO {

    // Method to get all borrowing details
    public List<BorrowDetailsDTO> getAllBorrowingDetails() throws SQLException {
        List<BorrowDetailsDTO> borrowDetailsList = new ArrayList<>();
        String query = "SELECT * FROM borrowing_details";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BorrowDetailsDTO borrowDetails = mapRowToBorrowDetails(rs);
                borrowDetailsList.add(borrowDetails);
            }
        }
        return borrowDetailsList;
    }

    // Method to get borrowing details by ID
    public BorrowDetailsDTO getBorrowingDetailsById(int id) throws SQLException {
        String query = "SELECT * FROM borrowing_details WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBorrowDetails(rs);
                }
            }
        }
        return null;
    }

    // Method to add borrowing details
    public void addBorrowingDetails(BorrowDetailsDTO borrowDetails) throws SQLException {
        String query = "INSERT INTO borrowing_details (book_id, borrow_id, returned) VALUES (?, ?, ?)";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, borrowDetails.getBookId());
            stmt.setInt(2, borrowDetails.getBorrowId());
            stmt.setBoolean(3, borrowDetails.isReturned());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    borrowDetails.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Method to update borrowing details
    public void updateBorrowingDetails(BorrowDetailsDTO borrowDetails) throws SQLException {
        String query = "UPDATE borrowing_details SET book_id = ?, borrow_id = ?, returned = ? WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, borrowDetails.getBookId());
            stmt.setInt(2, borrowDetails.getBorrowId());
            stmt.setBoolean(3, borrowDetails.isReturned());
            stmt.setInt(4, borrowDetails.getId());
            stmt.executeUpdate();
        }
    }

    // Method to delete borrowing details
    public void deleteBorrowingDetails(int id) throws SQLException {
        String query = "DELETE FROM borrowing_details WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a BorrowDetailsDTO object
    private BorrowDetailsDTO mapRowToBorrowDetails(ResultSet rs) throws SQLException {
        BorrowDetailsDTO borrowDetails = new BorrowDetailsDTO(
                rs.getInt("id"),
                rs.getInt("book_id"),
                rs.getInt("borrow_id"),
                rs.getBoolean("returned")
        );
        return borrowDetails;
    }
}