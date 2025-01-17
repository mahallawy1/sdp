/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Cmd.JoinEvent2VolunteerCmd;
import MODEL.Patterns.Command.Invoker;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.UserView;
import static java.lang.invoke.MethodHandles.invoker;
import java.sql.SQLException;

/**
 *
 * @author hussien
 */
public class addVolunteeringRecordState implements EventJoiningState{
       
 
   

    @Override
    public void handle(EventJoiningTemplateContext context) {
           try {
             Invoker invoker = new Invoker();
             // hussien please see this
            invoker.setCmd(new JoinEvent2VolunteerCmd(context.volunteeringManager, null, null));
            invoker.executeCmd();  // Use the manager to handle the record
            context.setState(new generateTicketState());
            
        } catch (SQLException e) {
            context.UI.showMessage("Error processing your volunteering record.");
            return;
        }
          
    }
    
}
