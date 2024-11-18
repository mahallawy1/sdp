package MODEL.Patterns.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventObserver4Volunteer extends AObserver {
    private String eventName;
    private String description;

    public EventObserver4Volunteer(EventSubject subject) {
        super(subject);
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void update() {
        EventSubject subj = (EventSubject)(super.subject);
        this.eventName = subj.eventName;
        this.description = subj.description;
        super.newNotification = true;

    }
}
