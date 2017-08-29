package amt.main.util;

/**
 *
 * @author Matthew Galan
 */
public class Force {
    
    private float x, y, xStep, yStep;
    private int t;
    private boolean done;
    
    public Force(float x, float y, int length) {
        this.x = x;
        this.y = y;
        t = length;
        xStep = x / length;
        yStep = y / length;
    }
    
    /**
     * Creates a force directed at position 2 from position 1.
     * @param x1 x position, in tiles, of position 1
     * @param y1 y position, in tiles, of position 1
     * @param x2 x position, in tiles, of position 2
     * @param y2 y position, in tiles, of position 2
     * @param magnitude The strength of the force
     * @param length Duration of the force, in updates
     */
    public Force(float x1, float y1, float x2, float y2, float magnitude, int length) {
        float dist = (float)Math.hypot(x1 - x2, y1 - y2);
        this.x = (x2 - x1) * magnitude / dist;
        this.y = (y2 - y1) * magnitude / dist;
        t = length;
        xStep = x / length;
        yStep = y / length;
    }

    public void update() {
        t--;
        if (t <=0) {
            done = true;
        } else {
            x -= xStep;
            y -= yStep;
        }
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public void resetX() {
        x = 0;
        xStep = 0;
    }
    
    public void resetY() {
        y = 0;
        yStep = 0;
    }
    
    public boolean done() {
        return done;
    }
}
