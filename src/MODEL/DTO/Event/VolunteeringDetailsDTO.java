/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Event;

/**
 *
 * @author mahallawy
 */
public class VolunteeringDetailsDTO {
    private int id;
    private Integer eventId;
    private Integer volunteeringId;
    private Integer hours;
    private String status; // Assuming status is a String as per the ENUM comment

    public VolunteeringDetailsDTO(int id,int eventId,int volunteeringId,int hours,String status) {
    this.id = id;
    this.eventId = eventId;
    this.volunteeringId = volunteeringId;
    this.hours = hours;
    this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getEventId() { return eventId; }
    public void setEventId(Integer eventId) { this.eventId = eventId; }

    public Integer getVolunteeringId() { return volunteeringId; }
    public void setVolunteeringId(Integer volunteeringId) { this.volunteeringId = volunteeringId; }

    public Integer getHours() { return hours; }
    public void setHours(Integer hours) { this.hours = hours; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
