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
import java.util.ArrayList;

/**
 *
 * @author hussien
 */
public class AdminEventFactory implements EventFactory{
    
    
    public EventDTO createEvent(UserDTO admin, String eventName,
            int eventTypeId,String description,LocalDate eventDate,LocalTime from, LocalTime to,ArrayList<Integer> skills
       ){
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
                // Set Capacity = admin event takes a 50 capacity room               
                event.setCapacity(50);
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
                
                for(int i = 0 ; i < skills.size();i++){
                        requiredSkill.setSkillId(skills.get(i));
                }
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
