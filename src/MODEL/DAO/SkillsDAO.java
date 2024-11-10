package MODEL.DAO;

import MODEL.DTO.Event.SkillDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillsDAO {

    // Add a skill to the database
    public static boolean addSkill(SkillDTO skill) throws SQLException {
        String sql = "INSERT INTO skill (id,name) VALUES (?,?)";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, skill.getId());
            pstmt.setString(2, skill.getName());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Remove a skill from the database
    public static boolean removeSkill(int skillId) throws SQLException {
        String sql = "DELETE FROM skill WHERE id = ?";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, skillId);
            return pstmt.executeUpdate() == 1;
        }
    }

    // Retrieve a list of all skills
    public static List<SkillDTO> getAllSkills() throws SQLException {
        String sql = "SELECT id, name FROM skill";
        List<SkillDTO> skillsList = new ArrayList<>();

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()) {
                SkillDTO skill = new SkillDTO(rset.getInt("id"), rset.getString("name"));
                skillsList.add(skill);
            }
        }
        return skillsList;
    }

    // Get a specific skill by ID
    public static SkillDTO getSkillById(int id) throws SQLException {
        String sql = "SELECT id, name FROM skill WHERE id = ?";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    return new SkillDTO(rset.getInt("id"), rset.getString("name"));
                }
            }
        }
        return null; // Return null if no skill is found with the given ID
    }
}
