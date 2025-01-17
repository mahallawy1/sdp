/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Adabter.EventJoiningAdapter;
import MODEL.Patterns.Adabter.TicketGenerator;
import MODEL.Patterns.Command.Manager.VolunteringManager;

/**
 *
 * @author hussien
 */
public class generateTicketState implements EventJoiningState {



  
    @Override
    public void handle(EventJoiningTemplateContext context) {
         if (context.volunteeringManager.isSuccessful()) {
            TicketGenerator eventTicket = new EventJoiningAdapter(context.volunteeringManager.getVolunteeringDetails(), context.event);
            eventTicket.saveTicketToFile( context.loggedInUser.getId() + ".txt");
            context.UI.showMessage("Your ticket has been saved.");
            context.setState(new requestEventState());
        } else {
            context.UI.showMessage("Error joining event.");
        }

    }
    
}
