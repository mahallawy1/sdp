package MODEL.Patterns.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventObserver extends AObserver{

    private String eventName;
    private LocalDate eventDate;
    private LocalTime timeFrom;

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    private LocalTime timeTo;

    public EventObserver(EventSubject subject) {
        super(subject);
    }

    @Override
    public void update() {
        EventSubject subj = (EventSubject)(super.subject);
        this.eventName = subj.eventName;
        this.eventDate = subj.eventDate;
        this.timeTo = subj.timeTo;
        this.timeFrom = subj.timeFrom;
        super.newNotification = true;

    }
}