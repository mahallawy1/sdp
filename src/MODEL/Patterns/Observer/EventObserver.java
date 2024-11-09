package MODEL.Patterns.Observer;

public class EventObserver extends AObserver{

    private String eventName;
    private String eventDate;
    private String timeFrom;
    private String timeTo;

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
        display();
    }

    @Override
    public void display() {
        System.out.println("New Event Added! " + eventName + " at " + eventDate +
                " from " + timeFrom + " to " + timeTo + ". See you \uD83D\uDE04.");
    }
}