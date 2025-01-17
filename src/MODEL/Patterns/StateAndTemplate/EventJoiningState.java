/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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
public interface EventJoiningState {
        void handle(EventJoiningTemplateContext context);

}
