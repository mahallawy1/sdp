package MODEL.Patterns.Observer;

import java.sql.Struct;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventSubject extends ASubject{
    public String eventName;
    protected LocalDate eventDate;
    protected LocalTime timeFrom;
    protected LocalTime timeTo;

    protected String description;

    public void setNotification(String eventName, LocalDate eventDate, LocalTime timeFrom, LocalTime timeTo, String description){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.description = description;
        super.notifyObservers();
    }
}