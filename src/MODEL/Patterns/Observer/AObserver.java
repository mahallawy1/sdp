package MODEL.Patterns.Observer;

public abstract class AObserver {
    protected ASubject subject;
    protected boolean newNotification;

    protected AObserver(ASubject subject){
        this.subject = subject;
        this.subject.addObserver(this);
        newNotification = false;
    }
    public abstract void update();

    public abstract void display();

}