package MODEL.Patterns.Observer;

public abstract class AObserver {
    protected ASubject subject;
    protected boolean newNotification;

    public boolean isNewNotification() {
        return newNotification;
    }

    protected AObserver(ASubject subject){
        this.subject = subject;
        this.subject.addObserver(this);
        newNotification = false;
    }
    public abstract void update();

    public void clearNotification(){
        newNotification = false;
    }

}