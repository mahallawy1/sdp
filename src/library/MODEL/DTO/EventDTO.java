/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author mahallawy
 */
public class EventDTO {
    private int id;
    private String name;
    private Integer eventTypeId;
    private String description;
    private LocalDate eventDate;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private Integer capacity;

    public EventDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getEventTypeId() { return eventTypeId; }
    public void setEventTypeId(Integer eventTypeId) { this.eventTypeId = eventTypeId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public LocalTime getTimeFrom() { return timeFrom; }
    public void setTimeFrom(LocalTime timeFrom) { this.timeFrom = timeFrom; }

    public LocalTime getTimeTo() { return timeTo; }
    public void setTimeTo(LocalTime timeTo) { this.timeTo = timeTo; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
