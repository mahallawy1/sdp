/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DAO.EventDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.UserView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hussien
 */
public class checkAvailabilityState implements EventJoiningState {

 

 

    @Override
    public void handle(EventJoiningTemplateContext context) {
           
        try {
            if (EventDAO.isEventFull(context.event.getId())) {
                context.userView.showMessage("Sorry, this event is already full.");
                return;
            }
            
        } catch (SQLException e) {
          System.out.println("Error checking if is event full : " + e);
        }
    }
    
}
