/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.factory;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.RoleDTO;

/**
 *
 * @author hussien
 */
public interface EventFactory {
    public EventDTO createEvent(RoleDTO admin,String eventType);
}
