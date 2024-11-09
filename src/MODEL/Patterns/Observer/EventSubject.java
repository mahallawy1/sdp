package MODEL.Patterns.Observer;

public class EventSubject extends ASubject{
    public String eventName;
    protected String eventDate;
    protected String timeFrom;
    protected String timeTo;

    public void setNotification(String eventName, String eventDate, String timeFrom, String timeTo){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        super.notifyObservers();
    }
}