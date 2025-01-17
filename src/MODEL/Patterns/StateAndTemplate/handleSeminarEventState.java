/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.InputHandler;
import View.UserView;
import View.UtilityHandler;

/**
 *
 * @author hussien
 */
public class handleSeminarEventState implements EventJoiningState{

 
    // Helper method to prompt for yes/no answers
    private boolean promptYesNo(EventJoiningTemplateContext context,String question) {
       
        while (true) {
            String answer = context.inputHandler.getInput(question).trim().toLowerCase();
            if (answer.equals("yes")) {
                return true;
            } else if (answer.equals("no")) {
                return false;
            } else {
                context.UI.showMessage("Invalid input. Please answer with 'yes' or 'no'.");
            }
        }
    }



    @Override
    public void handle(EventJoiningTemplateContext context) {
        context.UI.showMessage("You are joining a seminar event. Please answer the following survey to determine your eligibility:");

        // Example questions
        String question1 = "Do you have prior knowledge of the seminar topic? (yes/no)";
        String question2 = "Are you willing to actively participate during the seminar? (yes/no)";

        // Collect answers
        boolean knowsTopic = promptYesNo(context, question1);
        boolean willingToParticipate = promptYesNo(context, question2);
       

        // Decision logic
        if (knowsTopic && willingToParticipate ) {
            context.UI.showMessage("Congratulations! You meet the requirements to join the seminar.");
            context.setState(new requestVolunteeringDetailsState());
        } else {
            context.UI.showMessage("Unfortunately, you do not meet the requirements for this seminar.");
        }
    }
    
    
}
