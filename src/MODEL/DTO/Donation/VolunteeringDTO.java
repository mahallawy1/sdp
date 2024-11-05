/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Donation;

/**
 *
 * @author mahallawy
 */
public class VolunteeringDTO {
    private int id;
    private Integer eventId;
    private Integer userId;

    public VolunteeringDTO(int id, Integer eventId, Integer userId) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getEventId() { return eventId; }
    public void setEventId(Integer eventId) { this.eventId = eventId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
