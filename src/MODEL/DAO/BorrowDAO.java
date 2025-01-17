/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hussien
 */
package MODEL.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import MODEL.DTO.Book.BorrowDTO;

import MODEL.Patterns.singleton.DbConnectionSingleton;

public class BorrowDAO {

    // Method to get all borrow records
    public List<BorrowDTO> getAllBorrows() throws SQLException {
        List<BorrowDTO> borrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BorrowDTO borrow = mapRowToBorrow(rs);
                borrows.add(borrow);
            }
        }
        return borrows;
    }

    // Method to get a borrow record by its ID
    public BorrowDTO getBorrowById(int id) throws SQLException {
        String query = "SELECT * FROM borrows WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBorrow(rs);
                }
            }
        }
        return null;
    }

    // Method to add a new borrow record
    public void addBorrow(BorrowDTO borrow) throws SQLException {
        String query = "INSERT INTO borrows (user_id, days) VALUES (?, ?)";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, borrow.getUserId());
            stmt.setInt(2, borrow.getDays());
            stmt.executeUpdate();

            // Retrieve and set the generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    borrow.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Method to update an existing borrow record
    public void updateBorrow(BorrowDTO borrow) throws SQLException {
        String query = "UPDATE borrows SET user_id = ?, days = ? WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, borrow.getUserId());
            stmt.setInt(2, borrow.getDays());
            stmt.setInt(3, borrow.getId());

            stmt.executeUpdate();
        }
    }

    // Method to delete a borrow record
    public void deleteBorrow(int id) throws SQLException {
        String query = "DELETE FROM borrows WHERE id = ?";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Helper method to map a ResultSet row to a BorrowDTO object
    private BorrowDTO mapRowToBorrow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Integer userId = rs.getInt("user_id");
        if (rs.wasNull()) userId = null;
        Integer days = rs.getInt("days");
        if (rs.wasNull()) days = null;

        return new BorrowDTO(id, userId, days);
    }
}
