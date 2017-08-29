package amt.main.entities;

import amt.main.Handler;
import amt.main.util.Force;
import java.awt.Graphics;
import java.awt.Rectangle;
import amt.main.util.Rigidbody;

public abstract class Mob extends Entity {
    
    protected int health, maxHealth;
    protected float xMove, yMove, speed;
    protected Rigidbody body;
    
    public Mob (float x, float y, int maxHealth, float speed, Rectangle bounds, Handler handler) {
        super(x, y, bounds, handler);
        health = maxHealth;
        this.speed = speed;
        body = new Rigidbody();
        this.maxHealth = maxHealth; 
    }
    
    public void move(){
        body.update();
        float deltaX = xMove + body.getX();
        float deltaY = yMove + body.getY();
        if (deltaX > 0) { //Moving right
            float tempX = x + deltaX + xBound + boundWidth;
            if (!collisionWithTile(tempX, y + yBound) && !collisionWithTile(tempX, y + yBound + boundHeight)) {
                x += deltaX;
            } else {
                x = (int)tempX - xBound - boundWidth - .00001f;
            }
        } else if (deltaX < 0) { //Moving left
            float tempX = x + deltaX + xBound;
            if (!collisionWithTile(tempX, y + yBound) && !collisionWithTile(tempX, y + yBound + boundHeight)) {
                x += deltaX;
            } else {
                x = (int)tempX + 1.00001f - xBound;
            }
        }
        if (deltaY > 0) { //Moving down
            float tempY = y + deltaY + yBound + boundHeight;
            if (!collisionWithTile(x + xBound, tempY) && !collisionWithTile(x + xBound + boundWidth, tempY)) {
                y += deltaY;
            } else {
                y = (int)tempY - yBound - boundHeight - .00001f;
                body.resetY();
            }
        } else if (deltaY < 0) { //Moving up
            float tempY = y + deltaY + yBound;
            if (!collisionWithTile(x + xBound, tempY) && !collisionWithTile(x + xBound + boundWidth, tempY)) {
                y += deltaY;
            } else {
                y = (int)tempY + 1.00001f - yBound;
                body.resetY();
            }
        }
    }
    
    /**
     * Call when a projectile hits this mob.
     * @param damage The damage to do this mob
     * @param knockback The force of knockback
     */
    public void hit(int damage, Force knockback) {
        health -= damage;
        if (health <= 0) {
            destroy = true;
        }
        body.addForce(knockback);
    }

    /**
     * @return If the block .00001 tiles beneath the entity is solid.
     */
    protected boolean onGround() {
        return collisionWithTile(x + xBound, y + yBound + boundHeight + .00001f) || collisionWithTile(x + xBound + boundWidth, y + yBound + boundHeight + .00001f);
    }
    
    public abstract void update();
    
    public abstract void render(Graphics g);
}
