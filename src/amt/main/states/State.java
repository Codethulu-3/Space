package amt.main.states;

import java.awt.Graphics;

import amt.main.Handler;

/**
 *
 * @author asilence24
 */
public abstract class State {
    
    private static State currentState = null;
    
    public static void setState(State state) {
        currentState = state;
        currentState.reloadState();
    }

    public static State getState() {
        return currentState;
    }
    
    //Class
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void reloadState();
    public abstract void setLevel(String level);
}
