/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.factory;

import MODEL.DAO.EventDAO;
import MODEL.DAO.RequiredSkillsDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.RequiredSkillsDTO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author hussien
 */
public class VolunteerEventFactory implements EventFactory{

   
    public EventDTO createEvent(UserDTO volunteer, String eventName,int eventTypeId, String description, LocalDate eventDate, LocalTime from, LocalTime to) {
       {// if volunteer is creating the event
        if((volunteer.getRoleId())==2){
            // if event is seminar
                
                EventDTO event = new EventDTO() {}; // Create a new EventDTO object
                // Set ID                
                
                // Set Name
                event.setName(eventName);
                // Set Event Type ID to seminar always      
                eventTypeId = 0;
                event.setEventTypeId(eventTypeId);
                // Set Description                
                event.setDescription(description);
                // Set Event Date              
                event.setEventDate(eventDate);
               // Set Time From        
                event.setTimeFrom(from);
                // Set Time               
                event.setTimeTo(to);
                // Set Capacity = admin event takes a 25 capacity room               
                event.setCapacity(25);
                // Output the event details
                
                // skills with id 0 is required for seminar 
                
                int event_id = 0;
                try {
                    //CALL FOR APPROVAL Function processed by admin
                  event_id = EventDAO.addEvent(event);
                 System.out.println("Event added successfully with ID: " + event.getId());
                    
                 } catch (SQLException e) {
                         System.out.println("Error adding event: " + e.getMessage());

                 }
                RequiredSkillsDTO requiredSkill = new RequiredSkillsDTO();
                requiredSkill.setEventId(event_id);
                
                
                requiredSkill.setSkillId(0);
                
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
    
}
