/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package library.MODEL.DAO.Patterns.factory;

import library.MODEL.DTO.EventDTO;
import library.MODEL.DTO.RoleDTO;

/**
 *
 * @author hussien
 */
public interface EventFactory {
    public EventDTO createEvent(RoleDTO admin,String eventType);
}
