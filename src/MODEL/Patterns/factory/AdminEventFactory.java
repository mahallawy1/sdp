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
    
    
    public static EventDTO createEvent(UserDTO admin, String eventName,
            int eventTypeId,String description,LocalDate eventDate,LocalTime from, LocalTime to,
            int capacity){
        if((admin.getRoleId())==1){
            // if event is seminar
                
                EventDTO event = new EventDTO() {}; // Create a new EventDTO object
                // Set ID                
                
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
                
                // skills with id 0 is required for seminar 
                
                int event_id = 0;
                try {
                  event_id = EventDAO.addEvent(event);
                 System.out.println("Event added successfully with ID: " + event.getId());
                    
                 } catch (SQLException e) {
                         System.out.println("Error adding event: " + e.getMessage());

                 }
                RequiredSkillsDTO requiredSkill = new RequiredSkillsDTO();
                requiredSkill.setEventId(event_id);
                
                if(eventTypeId == 0){
                requiredSkill.setSkillId(0);
                }else{requiredSkill.setSkillId(1); 
                }// skill with id 1 which is required for workshop
                try{
                    int requiredSkill_id  = RequiredSkillsDAO.addRequiredSkill(requiredSkill);
                    System.out.println("RequiredSkills added successfully with ID: " + requiredSkill.getId());
                     
                
        
                }catch(SQLException e){
                    System.out.println("Error adding required skills: " + e.getMessage());

                }
                return event;
        }

        
        return null;
        
    }
    
}
