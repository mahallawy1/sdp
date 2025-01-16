/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.UserView;

/**
 *
 * @author hussien
 */
public class handleWorkshopEventState implements EventJoiningState {

    // Helper method to prompt for yes/no answers
    private boolean promptYesNo(UserView userView, String question) {
        while (true) {
            String answer = userView.getInput(question).trim().toLowerCase();
            if (answer.equals("yes")) {
                return true;
            } else if (answer.equals("no")) {
                return false;
            } else {
                userView.showMessage("Invalid input. Please answer with 'yes' or 'no'.");
            }
        }
    }

  

    @Override
    public void handle(EventJoiningTemplateContext context) {
          context.userView.showMessage("You are joining a workshop event. Please answer the following questions to determine your eligibility:");

        // Example workshop-specific questions
        String question1 = "Do you have prior experience in the workshop topic? (yes/no)";
        String question2 = "Are you available for the entire duration of the workshop? (yes/no)";
        String question3 = "Do you agree to complete the post-workshop assessment? (yes/no)";
        String question4 = "Are you willing to collaborate with other participants during the workshop? (yes/no)";
        String question5 = "Do you have the required resources (e.g., laptop, materials)? (yes/no)";

        // Collect answers
        boolean hasExperience = promptYesNo(context.userView, question1);
        boolean availableFullTime = promptYesNo(context.userView, question2);
        boolean agreesToAssessment = promptYesNo(context.userView, question3);
        boolean willingToCollaborate = promptYesNo(context.userView, question4);
        boolean hasResources  = promptYesNo(context.userView,question5);
        // Decision logic
        if (hasExperience && availableFullTime && agreesToAssessment && willingToCollaborate&&hasResources) {
           context.userView.showMessage("Congratulations! You meet the requirements to join the workshop.");
        } else {
            context.userView.showMessage("Unfortunately, you do not meet the requirements for this workshop.");
        }
    }
}
