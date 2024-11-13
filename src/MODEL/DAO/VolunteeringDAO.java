package MODEL.DAO;

import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolunteeringDAO {

    // Add a volunteering record to the database
    public static boolean addVolunteering(VolunteeringDTO volunteering) throws SQLException {
        String sql = "INSERT INTO volunteering (id, user_id) VALUES (?, ?)";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, volunteering.getId());
            pstmt.setInt(2, volunteering.getUserId());
            return pstmt.executeUpdate() == 1;
        }
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
    public static VolunteeringDTO getVolunteeringById(int id) throws SQLException {
        String sql = "SELECT id, user_id FROM volunteering WHERE id = ?";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    return new VolunteeringDTO(rset.getInt("id"), rset.getInt("user_id"));
                }
            }
        }
        return null; // Return null if no volunteering record is found with the given ID
    }
}
