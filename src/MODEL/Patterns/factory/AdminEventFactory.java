/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.factory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import MODEL.DAO.EventDAO;
import MODEL.DAO.RequiredSkillsDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.RequiredSkillsDTO;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;

/**
 *
 * @author hussien
 */
public class AdminEventFactory {
    private static int counter = 0;
    
    public static EventDTO createEvent(UserDTO admin, String eventName,
            int eventTypeId,String description,LocalDate eventDate,LocalTime from, LocalTime to,
            int capacity){
        if((admin.getRoleId())==1){
            // if event is seminar
                
                EventDTO event = new EventDTO() {}; // Create a new EventDTO object
                // Set ID                
                event.setId(counter++);
                // Set Name
                event.setName(eventName);
                // Set Event Type ID               
                event.setEventTypeId(eventTypeId);
                // Set Description                
                event.setDescription(description);
                // Set Event Date              
                event.setEventDate(eventDate);
               // Set Time From        
                event.setTimeFrom(from);
                // Set Time               
                event.setTimeTo(to);
                // Set Capacity               
                event.setCapacity(capacity);
                // Output the event details
                RequiredSkillsDTO requiredSkill = new RequiredSkillsDTO();
                requiredSkill.setEventId(counter);
                requiredSkill.setId(counter);
                // skills with id 0 is required for seminar 
                if(eventTypeId == 0){
                requiredSkill.setSkillId(0);
                }else{requiredSkill.setSkillId(1); }// skill with id 1 which is required for workshop
                try{
                    boolean isAdded  = RequiredSkillsDAO.addRequiredSkill(requiredSkill);
                    if (isAdded == true) {
                         System.out.println("RequiredSkills added successfully with ID: " + event.getId());
                     } else {
                         System.out.println("Failed to add RequiredSkills");
                }
                }catch(SQLException e){
                    System.out.println("Error adding required skills: " + e.getMessage());

                }
                try {
                 boolean isAdded = EventDAO.addEvent(event);
                     if (isAdded == true) {
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
