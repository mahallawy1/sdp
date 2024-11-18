package MODEL.Patterns.Observer;

import java.util.ArrayList;

public abstract class ASubject {

    private ArrayList<AObserver> observers;

    public ASubject() {
        observers = new ArrayList<AObserver>();
    }

    public void addObserver(AObserver o){
        observers.add(o);
// ///////////////////////       System.out.println(observers.size());
    }

    public void removeObserver(AObserver o){
        int ind = observers.indexOf(o);
        if(ind >=0)
            observers.remove(ind);
    }

    public void notifyObservers(){
        for(AObserver o : observers)
            o.update();
    }
}