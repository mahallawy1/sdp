package MODEL.Patterns.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventObserver extends AObserver{

    private String eventName;
    private LocalDate eventDate;
    private LocalTime timeFrom;
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

    @Override
    public void display() {
        if(super.newNotification) {
            System.out.println("\uD83D\uDD14  New Event Added! " + eventName + " at " + eventDate +
                    " from " + timeFrom + " to " + timeTo + ". See you \uD83D\uDE04.");
            super.newNotification = false;
        }
    }
}