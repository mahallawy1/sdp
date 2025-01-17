/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DAO.EventDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.InputHandler;
import View.UserView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hussien
 */
public class requestEventState implements EventJoiningState{

    @Override
    public void handle(EventJoiningTemplateContext context) {
       
         int eventId = Integer.parseInt(context.inputHandler.getInput("Enter the ID of the event you wish to join: "));
        try {
            context.event = EventDAO.getEventById(eventId);
            context.setState(new checkAvailabilityState());
           
        } catch (SQLException e) {
            System.out.println("Error getting event : "+ e);
        }
        if (context.event == null) {
            context.UI.showMessage("Event not found.");
            return;
        }
    }
    
}
