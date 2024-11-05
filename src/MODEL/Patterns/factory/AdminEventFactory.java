/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.factory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import MODEL.DAO.EventDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;

/**
 *
 * @author hussien
 */
public class AdminEventFactory {
    
    
    public static EventDTO createEvent(UserDTO admin,int eventId, String eventName,
            int eventTypeId,String description,LocalDate eventDate,LocalTime from, LocalTime to,
            int capacity){
        if((admin.getRoleId())==1){
          
                Scanner scanner = new Scanner(System.in);
                EventDTO event = new EventDTO() {}; // Create a new EventDTO object
                event.s
                // Set ID
                System.out.print("Enter event ID: ");
                event.setId(eventId);
                scanner.nextLine(); // Consume newline left-over

                // Set Name
                System.out.print("Enter event name: ");
                event.setName(eventName);

                // Set Event Type ID
                System.out.print("Enter event type ID: ");
                event.setEventTypeId(eventTypeId);

                scanner.nextLine(); // Consume newline left-over

                // Set Description
                System.out.print("Enter event description: ");
                event.setDescription(description);

                // Set Event Date
                System.out.print("Enter event date (YYYY-MM-DD): ");
                event.setEventDate(eventDate);

                // Set Time From
                System.out.print("Enter start time (HH:MM): ");
                event.setTimeFrom(from);

                // Set Time To
                System.out.print("Enter end time (HH:MM): ");
                event.setTimeTo(to);

                // Set Capacity
                System.out.print("Enter event capacity: ");
                event.setCapacity(capacity);

                // Output the event details
              
                   try {
                    boolean isAdded = EventDAO.addEvent(event);
                        if (isAdded == false) {
                            System.out.println("Event added successfully with ID: " + event.getId());
                        } else {
                            System.out.println("Failed to add event.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error adding event: " + e.getMessage());
                  
                    }

                
                return event;
        }
            
        
        return null;
        
    }
    
}
