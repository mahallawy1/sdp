package MODEL.Patterns.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventObserver4Volunteer extends AObserver {
    private String eventName;
    private String description;

    public EventObserver4Volunteer(EventSubject subject) {
        super(subject);
    }

    @Override
    public void update() {
        EventSubject subj = (EventSubject)(super.subject);
        this.eventName = subj.eventName;
        this.description = subj.description;
        super.newNotification = true;

    }

    @Override
    public void display() {
        if(super.newNotification) {
            System.out.println("\uD83D\uDD14  New Event Added! \n Event Name: " + eventName + "\n Description: " + description +
                    ".\nSee you \uD83D\uDE04.");
            super.newNotification = false;
        }
    }
}
