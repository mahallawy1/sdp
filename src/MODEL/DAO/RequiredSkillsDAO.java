package MODEL.DAO;

import MODEL.DTO.Event.RequiredSkillsDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequiredSkillsDAO {

    // Add a required skill for an event
    public static boolean addRequiredSkill(RequiredSkillsDTO requiredSkill) throws SQLException {
        String sql = "INSERT INTO required_skills_id (event_id, skill_id) VALUES (?, ?)";
        
        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, requiredSkill.getEventId());
            pstmt.setInt(2, requiredSkill.getSkillId());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Remove a required skill from an event
    public static boolean removeRequiredSkill(RequiredSkillsDTO requiredSkill) throws SQLException {
        String sql = "DELETE FROM required_skills_id WHERE event_id = ? AND skill_id = ?";
        
        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, requiredSkill.getEventId());
            pstmt.setInt(2, requiredSkill.getSkillId());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Retrieve all required skills for a specific event as a list of RequiredSkillsDTO objects
    public static List<RequiredSkillsDTO> getRequiredSkillsForEvent(int eventId) throws SQLException {
        String sql = "SELECT id, event_id, skill_id FROM required_skills_id WHERE event_id = ?";
        List<RequiredSkillsDTO> requiredSkillsList = new ArrayList<>();
        
        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    RequiredSkillsDTO requiredSkill = new RequiredSkillsDTO();
                    requiredSkill.setId(rset.getInt("id"));
                    requiredSkill.setEventId(rset.getInt("event_id"));
                    requiredSkill.setSkillId(rset.getInt("skill_id"));
                    requiredSkillsList.add(requiredSkill);
                }
            }
        }
        return requiredSkillsList;
    }

    // Check if an event requires a specific skill using RequiredSkillsDTO
    public static boolean isSkillRequired(RequiredSkillsDTO requiredSkill) throws SQLException {
        String sql = "SELECT 1 FROM required_skills_id WHERE event_id = ? AND skill_id = ?";
        
        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, requiredSkill.getEventId());
            pstmt.setInt(2, requiredSkill.getSkillId());
            try (ResultSet rset = pstmt.executeQuery()) {
                return rset.next();
            }
        }
    }

    // Get a specific RequiredSkillsDTO by id
    public static RequiredSkillsDTO getRequiredSkillById(int id) throws SQLException {
        String sql = "SELECT id, event_id, skill_id FROM required_skills_id WHERE id = ?";
        
        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    RequiredSkillsDTO requiredSkill = new RequiredSkillsDTO();
                    requiredSkill.setId(rset.getInt("id"));
                    requiredSkill.setEventId(rset.getInt("event_id"));
                    requiredSkill.setSkillId(rset.getInt("skill_id"));
                    return requiredSkill;
                }
            }
        }
        return null;
    }
}
