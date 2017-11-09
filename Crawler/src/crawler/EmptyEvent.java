package crawler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmptyEvent {
    
    public int a=0;
    public void addA(int a){this.a = a;}

    private List<IEmptyListener> listeners = new ArrayList<>();

    public void fire(Object source) {
        for (IEmptyListener el : this.listeners) {
            el.handle(source);
        }
    }

    public void add(IEmptyListener listener) {
        this.listeners.add(listener);
    }

    public void remove(IEmptyListener listener) {
        this.listeners.remove(listener);
    }
}
