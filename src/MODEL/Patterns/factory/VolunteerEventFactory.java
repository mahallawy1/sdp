/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.factory;

import MODEL.DAO.EventDAO;
import MODEL.DAO.RequiredSkillsDAO;
import MODEL.DAO.UserDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.RequiredSkillsDTO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import View.UserView;

/**
 *
 * @author hussien
 */
import View.UserView;

public class VolunteerEventFactory implements EventFactory{

    private UserView userView;
    public VolunteerEventFactory(UserView userView) {
        this.userView = userView;
    }

    public EventDTO createEvent(UserDTO volunteer, String eventName,int eventTypeId, String description, LocalDate eventDate, LocalTime from, LocalTime to,ArrayList<Integer> skills) {
       // if volunteer is creating the event
        if(volunteer.getRoleId()==2){
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
                // Set Capacity = volunteer event takes a 25 capacity room               
                event.setCapacity(25);
                // Output the event details
                
               
                
                int event_id = 0;
                 userView.showMessage("Calling for Admin approval");//
                 if(UserDAO.callForEventApproval()){
                try {
                
                 event_id = EventDAO.addEvent(event);
                 userView.showMessage("Event added successfully with ID: " + event.getId());//
                    
                 } catch (SQLException e) {
                         userView.showMessage("Error adding event: " + e.getMessage());//

                 }
               
                
                  for(int i = 0 ; i < skills.size();i++){
                       RequiredSkillsDTO requiredSkill = new RequiredSkillsDTO();
                        requiredSkill.setEventId(event_id);
                        requiredSkill.setSkillId(skills.get(i));
                        try{
                            int requiredSkill_id  = RequiredSkillsDAO.addRequiredSkill(requiredSkill);
                        //
                            userView.showMessage("RequiredSkills added successfully with ID: " + requiredSkill.getId());
                        }
                             catch(SQLException e){
                               userView.showMessage("Error adding required skills: " + e.getMessage());//

                }
                }
           
                return event;
                }
        }
        else{

        
        return null;
        
    }
        return null;
    }
    
    
}
