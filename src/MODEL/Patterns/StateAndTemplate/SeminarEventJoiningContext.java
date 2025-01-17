/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.StateAndTemplate;

import MODEL.DTO.User.UserDTO;
import View.UserView;

/**
 *
 * @author hussien
 */
// Seminar-specific event joining
public class SeminarEventJoiningContext extends EventJoiningTemplateContext {

    public SeminarEventJoiningContext(UserDTO loggedInUser) {
        super(loggedInUser);
        
    }
    
    


    @Override
    protected void handleSpecificEventActions() {
        super.setState(new handleSeminarEventState());
                
        super.currentState.handle(this);
    }
}



