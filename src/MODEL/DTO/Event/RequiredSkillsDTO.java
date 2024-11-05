/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Event;

/**
 *
 * @author mahallawy
 */
public class RequiredSkillsDTO {
    private int id;
    private Integer eventId;
    private Integer skillId;

    public RequiredSkillsDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getEventId() { return eventId; }
    public void setEventId(Integer eventId) { this.eventId = eventId; }

    public Integer getSkillId() { return skillId; }
    public void setSkillId(Integer skillId) { this.skillId = skillId; }
}
