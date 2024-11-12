package MODEL.Patterns.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventSubject extends ASubject{
    public String eventName;
    protected LocalDate eventDate;
    protected LocalTime timeFrom;
    protected LocalTime timeTo;

    public void setNotification(String eventName, LocalDate eventDate, LocalTime timeFrom, LocalTime timeTo){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        super.notifyObservers();
    }
}