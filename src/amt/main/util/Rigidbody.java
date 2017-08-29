package amt.main.util;

import java.util.HashSet;

public class Rigidbody {
    
    private HashSet<Force> forces, doneForces;
    
    public Rigidbody() {
        forces = new HashSet<>();
        doneForces = new HashSet<>();
    }
    
    public void update() {
        for (Force f : forces) {
            if (f.done())
                doneForces.add(f);
        }
        forces.removeAll(doneForces);
        doneForces.clear();
        for (Force f : forces) {
            f.update();
        }
    }
    
    public void addForce(Force force) {
        forces.add(force);
    }
    
    public float getX() {
        float sum = 0;
        for (Force f : forces) {
            sum += f.getX();
        }
        return sum;
    }
    
    public float getY() {
        float sum = 0;
        for (Force f : forces) {
            sum += f.getY();
        }
        return sum + .1f;
    }
    
    public void resetX() {
        for (Force f : forces) {
            f.resetX();
        }
    }
    
    public void resetY() {
        for (Force f : forces) {
            f.resetY();
        }
    }
}
