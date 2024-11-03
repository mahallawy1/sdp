package library.MODEL.DAO;

import library.MODEL.DBUtil.DBUtil;
import library.MODEL.DTO.EventDTO;
import library.MODEL.DTO.SkillDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // Check if the event is full based on its capacity
    public static boolean isEventFull(int eventId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT capacity FROM events WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eventId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                int capacity = rset.getInt("capacity");
                String sqlCount = "SELECT COUNT(*) AS count FROM event_attendees WHERE event_id = ?";
                pstmt = conn.prepareStatement(sqlCount);
                pstmt.setInt(1, eventId);
                rset = pstmt.executeQuery();
                
                if (rset.next()) {
                    int attendeeCount = rset.getInt("count");
                    return attendeeCount >= capacity;
                }
            }
        } finally {
            DBUtil.close(conn, pstmt, rset);
        }
        return false;
    }

    // Add a required skill for an event
    public static boolean addRequiredSkill(int eventId, SkillDTO skill) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO event_skills (event_id, skill_id) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, skill.getId());
            int result = pstmt.executeUpdate();
            return result == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    // Remove a required skill from an event
    public static boolean removeRequiredSkill(int eventId, SkillDTO skill) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM event_skills WHERE event_id = ? AND skill_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, skill.getId());
            int result = pstmt.executeUpdate();
            return result == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    // Check if an event has all required skills from a given list
    public static boolean hasRequiredSkills(int eventId, List<SkillDTO> skills) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT skill_id FROM event_skills WHERE event_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eventId);
            rset = pstmt.executeQuery();

            // Collect skill IDs required for the event
            List<Integer> requiredSkills = new ArrayList<>();
            while (rset.next()) {
                requiredSkills.add(rset.getInt("skill_id"));
            }

            // Check if all skills in `skills` are in the `requiredSkills` list
            for (SkillDTO skill : skills) {
                if (!requiredSkills.contains(skill.getId())) {
                    return false;
                }
            }
            return true;
        } finally {
            DBUtil.close(conn, pstmt, rset);
        }
    }

    // Add a new event
    public static boolean addEvent(EventDTO event) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO events (name, eventTypeId, description, eventDate, timeFrom, timeTo, capacity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getName());
            pstmt.setInt(2, event.getEventTypeId());
            pstmt.setString(3, event.getDescription());
            pstmt.setObject(4, event.getEventDate());
            pstmt.setObject(5, event.getTimeFrom());
            pstmt.setObject(6, event.getTimeTo());
            pstmt.setInt(7, event.getCapacity());
            int result = pstmt.executeUpdate();
            return result == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    // Remove an event by its ID
    public static boolean removeEvent(int eventId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM events WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eventId);
            int result = pstmt.executeUpdate();
            return result == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }
}