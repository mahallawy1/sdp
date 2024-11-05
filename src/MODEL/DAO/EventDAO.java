package MODEL.DAO;

import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Donation.SkillDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // Private static instance of EventDAO
    private static EventDAO instance;

    // Private constructor to prevent instantiation
    private EventDAO() {}

    // Public method to provide access to the single instance
    public static synchronized EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    // Check if the event is full based on its capacity
    public boolean isEventFull(int eventId) throws SQLException {
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT capacity FROM events WHERE id = ?")) {

            pstmt.setInt(1, eventId);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    int capacity = rset.getInt("capacity");

                    try (PreparedStatement countStmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM event_attendees WHERE event_id = ?")) {
                        countStmt.setInt(1, eventId);
                        try (ResultSet countRset = countStmt.executeQuery()) {
                            if (countRset.next()) {
                                int attendeeCount = countRset.getInt("count");
                                return attendeeCount >= capacity;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Add a required skill for an event
    public boolean addRequiredSkill(int eventId, SkillDTO skill) throws SQLException {
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO event_skills (event_id, skill_id) VALUES (?, ?)")) {

            pstmt.setInt(1, eventId);
            pstmt.setInt(2, skill.getId());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Remove a required skill from an event
    public boolean removeRequiredSkill(int eventId, SkillDTO skill) throws SQLException {
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM event_skills WHERE event_id = ? AND skill_id = ?")) {

            pstmt.setInt(1, eventId);
            pstmt.setInt(2, skill.getId());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Check if an event has all required skills from a given list
    public boolean hasRequiredSkills(int eventId, List<SkillDTO> skills) throws SQLException {
        List<Integer> requiredSkills = new ArrayList<>();

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT skill_id FROM event_skills WHERE event_id = ?")) {

            pstmt.setInt(1, eventId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    requiredSkills.add(rset.getInt("skill_id"));
                }
            }
        }

        for (SkillDTO skill : skills) {
            if (!requiredSkills.contains(skill.getId())) {
                return false;
            }
        }
        return true;
    }

    // Add a new event
    public static boolean addEvent(EventDTO event) throws SQLException {
        String sql = "INSERT INTO event (name, eventTypeId, description, eventDate, timeFrom, timeTo, capacity) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, event.getName());
            pstmt.setInt(2, event.getEventTypeId());
            pstmt.setString(3, event.getDescription());
            pstmt.setObject(4, event.getEventDate());
            pstmt.setObject(5, event.getTimeFrom());
            pstmt.setObject(6, event.getTimeTo());
            pstmt.setInt(7, event.getCapacity());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Remove an event by its ID
    public boolean removeEvent(int eventId) throws SQLException {
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM events WHERE id = ?")) {

            pstmt.setInt(1, eventId);
            return pstmt.executeUpdate() == 1;
        }
    }
}
