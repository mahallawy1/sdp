/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.factory;

import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author hussien
 */
public interface EventFactory {
     public  EventDTO createEvent(UserDTO admin, String eventName,
            int eventTypeId,String description,LocalDate eventDate,LocalTime from, LocalTime to,ArrayList<Integer>skills
            );
}
