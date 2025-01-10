/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Adabter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/////////////////////
import Controller.UserController;
import MODEL.DAO.VolunteeringDAO;
import MODEL.DAO.VolunteeringDetailsDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;
import MODEL.DTO.User.UserDTO;
import View.UserView;
import java.sql.SQLException;

/**
 *
 * @author mahallawy
 */
public class EventJoiningAdapter implements TicketGenerator {
    private final VolunteeringDetailsDTO details;
    private final EventDTO event;

    public EventJoiningAdapter(VolunteeringDetailsDTO details, EventDTO event) {
        this.details = details;
        this.event = event;
    }

    @Override
    public String generateTicket() {
        StringBuilder ticket = new StringBuilder();
        ticket.append("Event Joining Ticket\n");
        ticket.append("Event Name: ").append(event.getName()).append("\n");
        ticket.append("Event Date: ").append(event.getEventDate()).append("\n");
        ticket.append("User ID: ").append(details.getVolunteeringId()).append("\n");
        ticket.append("Volunteer Hours: ").append(details.getHours()).append("\n");
        ticket.append("Status: ").append(details.getStatus()).append("\n");
        return ticket.toString();
    }

    @Override
    public void saveTicketToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(generateTicket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
