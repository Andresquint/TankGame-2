package TankGame;

import java.awt.event.KeyEvent;
import java.util.Observable;

public class GameEvents extends Observable {
    Object event;
    int keyType;

    public GameEvents() {

    }

    public Object get_KeyEvent() {
        return event;
    }

    public void set_KeyEvent(KeyEvent e) {
        keyType = 1;
        event = e;
        setChanged();
        notifyObservers(event);
    }

    public void set_EventMsg(String msg) {
        event = msg;
        setChanged();
        notifyObservers(event);
    }
}
