package MODEL.DAO;

import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VolunteeringDAO {

    // Add a volunteering record to the database with auto-generated ID
    public static boolean addVolunteering(VolunteeringDTO volunteering) throws SQLException {
        String sql = "INSERT INTO volunteering (user_id) VALUES (?)";
        int generatedId = -1;

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, volunteering.getUserId());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 1) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        volunteering.setId(generatedId);
                        return true;
                    }
                }
            }
        }
       return false; // Returns the generated ID or -1 if the insertion failed
    }

    // Remove a volunteering record from the database
    public static boolean removeVolunteering(int volunteeringId) throws SQLException {
        String sql = "DELETE FROM volunteering WHERE id = ?";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, volunteeringId);
            return pstmt.executeUpdate() == 1;
        }
    }

    // Retrieve a list of all volunteering records
    public static List<VolunteeringDTO> getAllVolunteering() throws SQLException {
        String sql = "SELECT id, user_id FROM volunteering";
        List<VolunteeringDTO> volunteeringList = new ArrayList<>();

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()) {
                VolunteeringDTO volunteering = new VolunteeringDTO(rset.getInt("id"), rset.getInt("user_id"));
                volunteeringList.add(volunteering);
            }
        }
        return volunteeringList;
    }

    // Get a specific volunteering record by ID
    public static VolunteeringDTO getVolunteeringByUserId(int userId) throws SQLException {
        // Query filtering by user_id
        String sql = "SELECT id, user_id FROM volunteering WHERE user_id = ?";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId); // Using the user_id

            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    return new VolunteeringDTO(rset.getInt("id"), rset.getInt("user_id"));
                }
            }
        }
        return null; // Return null if no matching record is found
    }
}