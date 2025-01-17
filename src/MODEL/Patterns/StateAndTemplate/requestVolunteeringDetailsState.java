/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.UserView;

/**
 *
 * @author hussien
 */
public class requestVolunteeringDetailsState implements EventJoiningState{

 

    @Override
    public void handle(EventJoiningTemplateContext context) {
          int volunteerHours = Integer.parseInt(context.inputHandler.getInput("Enter how many hours you are willing to volunteer for: "));
        VolunteeringDetailsDTO details = new VolunteeringDetailsDTO(context.event.getId(), context.loggedInUser.getId(), volunteerHours, "pending");
        VolunteeringDTO volunteering = new VolunteeringDTO(context.event.getId(), context.loggedInUser.getId());

        // Create a new VolunteeringManager instance
        context.volunteeringManager = new VolunteringManager(volunteering, details);
        
        context.setState(new addVolunteeringRecordState());
    }
    
    
    
}
