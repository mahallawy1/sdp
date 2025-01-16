/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DAO;

/**
 *
 * @author hussien
 */


import MODEL.DTO.Event.EventTypeDTO;
import MODEL.Patterns.singleton.DbConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventTypeDAO {

    // Method to initialize event types in the database
    public void initializeEventTypes() throws SQLException {
  String checkQuery = "SELECT COUNT(*) FROM event_type WHERE id = ?";
try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
     PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

    for (int id = 0; id <= 1; id++) {
        checkStmt.setInt(1, id);
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) { // No record exists with this ID
                String insertQuery = "INSERT INTO event_type (id, type) VALUES (?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, id);
                    insertStmt.setString(2, id == 0 ? "Seminar" : "Workshop");
                    insertStmt.executeUpdate();
                }
            }
        }
    }
}

    }

    // Method to add a new event type
    public void addEventType(EventTypeDTO eventType) throws SQLException {
        String query = "INSERT INTO event_type (id, type) VALUES (?, ?)";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, eventType.getId());
            stmt.setString(2, eventType.getType());
            stmt.executeUpdate();
        }
    }

    // Method to get an event type by ID
    public EventTypeDTO getEventTypeById(int id) throws SQLException {
        String query = "SELECT id, type FROM event_type WHERE id = ?";
        EventTypeDTO eventType = null;

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    eventType = new EventTypeDTO();
                    eventType.setId(rs.getInt("id"));
                    eventType.setType(rs.getString("type"));
                }
            }
        }
        return eventType;
    }

    // Method to get all event types
    public List<EventTypeDTO> getAllEventTypes() throws SQLException {
        String query = "SELECT id, type FROM event_type";
        List<EventTypeDTO> eventTypes = new ArrayList<>();

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EventTypeDTO eventType = new EventTypeDTO();
                eventType.setId(rs.getInt("id"));
                eventType.setType(rs.getString("type"));
                eventTypes.add(eventType);
            }
        }
        return eventTypes;
    }

    // Method to delete an event type by ID
    public void deleteEventType(int id) throws SQLException {
        String query = "DELETE FROM event_type WHERE id = ?";

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
