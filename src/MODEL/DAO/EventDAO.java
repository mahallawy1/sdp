package MODEL.DAO;

import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.SkillDTO;

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
    public static boolean isEventFull(int eventId) throws SQLException {
       
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT capacity FROM event WHERE id = ?")) {

            pstmt.setInt(1, eventId);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    int capacity = rset.getInt("capacity");
 return false;
 
 /// hussien check this plzzz
                    /*try (PreparedStatement countStmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM event_attendees WHERE event_id = ?")) {
 
                        countStmt.setInt(1, eventId);
                        try (ResultSet countRset = countStmt.executeQuery()) {
                            if (countRset.next()) {
                                int attendeeCount = countRset.getInt("count");
                                return attendeeCount >= capacity;
                            }
                        }
                    }*/
                }
            }
        }
        return false;
    }
public static List<EventDTO> getEventsByType(int eventTypeId) throws SQLException {
        List<EventDTO> events = new ArrayList<>();
        String query = "SELECT * FROM event WHERE event_type_id = ?";
        
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            
            statement.setInt(1, eventTypeId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EventDTO event = new EventDTO();
                    event.setId(resultSet.getInt("id"));
                    event.setName(resultSet.getString("name"));
                    event.setEventTypeId(resultSet.getInt("event_type_id"));
                    event.setDescription(resultSet.getString("description"));
                    event.setEventDate(resultSet.getDate("event_date").toLocalDate());
                    event.setTimeFrom(resultSet.getTime("time_from").toLocalTime());
                    event.setTimeTo(resultSet.getTime("time_to").toLocalTime());
                    event.setCapacity(resultSet.getInt("capacity"));
                    events.add(event);
                }
            }
        }
        return events;
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
    public static boolean removeRequiredSkill(int eventId, SkillDTO skill) throws SQLException {
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM event_skills WHERE event_id = ? AND skill_id = ?")) {

            pstmt.setInt(1, eventId);
            pstmt.setInt(2, skill.getId());
            return pstmt.executeUpdate() == 1;
        }
    }

    // Check if an event has all required skills from a given list
    public static boolean hasRequiredSkills(int eventId, List<SkillDTO> skills) throws SQLException {
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

    // Add a new event and return the generated ID
    public static int addEvent(EventDTO event) throws SQLException {
        String sql = "INSERT INTO event (name, event_type_id, description, event_date, time_from, time_to, capacity) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, event.getName());
            pstmt.setInt(2, event.getEventTypeId());
            pstmt.setString(3, event.getDescription());
            pstmt.setObject(4, event.getEventDate());
            pstmt.setObject(5, event.getTimeFrom());
            pstmt.setObject(6, event.getTimeTo());
            pstmt.setInt(7, event.getCapacity());

            pstmt.executeUpdate();

            // Retrieve the generated event ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    event.setId(generatedKeys.getInt(1));
                    return generatedKeys.getInt(1); // Return the generated event ID
                } else {
                    throw new SQLException("Creating event failed, no ID obtained.");
                }
            }
        }
    }
public static EventDTO getEventById(int eventId) throws SQLException {
    String sql = "SELECT id, name, event_type_id, description, event_date, time_from, time_to, capacity FROM event WHERE id = ?";
    try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, eventId);
        try (ResultSet rset = pstmt.executeQuery()) {
            if (rset.next()) {
                EventDTO event = new EventDTO();
                event.setId(rset.getInt("id"));
                event.setName(rset.getString("name"));
                event.setEventTypeId(rset.getInt("event_type_id"));
                event.setDescription(rset.getString("description"));
                event.setEventDate(rset.getDate("event_date").toLocalDate());
                event.setTimeFrom(rset.getTime("time_from").toLocalTime());
                event.setTimeTo(rset.getTime("time_to").toLocalTime());
                event.setCapacity(rset.getInt("capacity"));
                return event;
            }
        }
    }
    return null; // Return null if no event is found
}

    // Remove an event by its ID
    public static boolean removeEvent(int eventId) throws SQLException {
        try (Connection conn = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM event WHERE id = ?")) {

            pstmt.setInt(1, eventId);
            return pstmt.executeUpdate() == 1;
        }
    }
}
