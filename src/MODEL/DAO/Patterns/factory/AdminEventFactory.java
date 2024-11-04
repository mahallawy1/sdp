/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DAO.Patterns.factory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import MODEL.DAO.EventDAO;
import MODEL.DTO.EventDTO;
import MODEL.DTO.RoleDTO;
import java.sql.SQLException;

/**
 *
 * @author hussien
 */
public class AdminEventFactory {
    
    
    public static EventDTO createEvent(RoleDTO admin){
        if("admin".equals(admin.getName())){
          
                Scanner scanner = new Scanner(System.in);
                EventDTO event = new EventDTO() {}; // Create a new EventDTO object

                // Set ID
                System.out.print("Enter event ID: ");
                event.setId(scanner.nextInt());
                scanner.nextLine(); // Consume newline left-over

                // Set Name
                System.out.print("Enter event name: ");
                event.setName(scanner.nextLine());

                // Set Event Type ID
                System.out.print("Enter event type ID: ");
                event.setEventTypeId(scanner.nextInt());

                scanner.nextLine(); // Consume newline left-over

                // Set Description
                System.out.print("Enter event description: ");
                event.setDescription(scanner.nextLine());

                // Set Event Date
                System.out.print("Enter event date (YYYY-MM-DD): ");
                event.setEventDate(LocalDate.parse(scanner.nextLine()));

                // Set Time From
                System.out.print("Enter start time (HH:MM): ");
                event.setTimeFrom(LocalTime.parse(scanner.nextLine()));

                // Set Time To
                System.out.print("Enter end time (HH:MM): ");
                event.setTimeTo(LocalTime.parse(scanner.nextLine()));

                // Set Capacity
                System.out.print("Enter event capacity: ");
                event.setCapacity(scanner.nextInt());

                // Output the event details
                System.out.println("\nEvent Details:");
                System.out.println("ID: " + event.getId());
                System.out.println("Name: " + event.getName());
                System.out.println("Event Type ID: " + event.getEventTypeId());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Event Date: " + event.getEventDate());
                System.out.println("Time From: " + event.getTimeFrom());
                System.out.println("Time To: " + event.getTimeTo());
                System.out.println("Capacity: " + event.getCapacity());

                scanner.close(); // Close the scanner
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
