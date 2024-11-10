package MODEL.Patterns.Observer;

public abstract class AObserver {
    protected ASubject subject;

    protected AObserver(ASubject subject){
        this.subject = subject;
        this.subject.addObserver(this);
    }
    public abstract void update();

    public abstract void display();

}