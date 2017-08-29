package amt.main.entities;

import amt.main.Handler;
import amt.main.gfx.Assets;
import amt.main.util.Force;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.shape.Circle;

/**
 *
 * @author Matthew Galan
 */
public class Rusher extends Mob {
  
    private final float EXPLOSION_RADIUS = 6f, //How big is the explosion?
            EXPLOSION_DETECTION = 2f; //How close do we get before we start to explode?
    private final long EXPLOSION_DELAY = 1500; //How long to wait before exploding.
    private final int EXPLOSION_DAMAGE = 5, KNOCKBACK_DURATION = 60;
    private final float EXPLOSION_KNOCKBACK = .3f;
    
    private int explosionTimer = 120;
    
    private Player player;
    private Circle explosionCheck, explosion;
    private boolean exploding, exploded, damageDealt;
    private long explosionStart;
    
    public Rusher(float x, float y, Handler handler) {
        //x, y, max health, speed, bounds, handler
        super(x, y, 15, .1f, new Rectangle(7, 9, 50, 45), handler);
    }
    
    @Override
    public void update() {
        if (!exploding) {
            if (handler.getScene().getPlayer().getCenterX() > getCenterX()) { //Player is further right, run right.
                xMove = speed;
                if (onGround() && handler.getScene().getTile(x + xBound + boundWidth + .2f, getCenterY()).isSolid()) { //If there's a solid tile to the right of us,
                    body.addForce(new Force(0, -0.28f, 60)); //Jump
                }
            } else { //Player is further left, run left.
                xMove = -speed;
                if (onGround() && handler.getScene().getTile(x + xBound - .2f, getCenterY()).isSolid()) { //If there's a solid tile to the left of us,
                    body.addForce(new Force(0, -0.28f, 60)); //Jump
                }
            }
            if (Math.hypot(getCenterX() - handler.getScene().getPlayer().getCenterX(), getCenterY() - handler.getScene().getPlayer().getCenterY()) < EXPLOSION_DETECTION) {
                exploding = true;
                explosionStart = System.currentTimeMillis();
            }
        } else {
            xMove = 0;
            yMove = 0;
            if (System.currentTimeMillis() - explosionStart > EXPLOSION_DELAY) {
                //Explode!
                exploded = true;
            }
        }
        if (!exploded) {
            move();
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.red);
        //g.drawRect((int)((x - handler.getCamera().xOffset()) * Assets.tileWidth) + bounds.x, (int)((y - handler.getCamera().yOffset()) * Assets.tileHeight) + bounds.y, bounds.width, bounds.height);
        if (exploded) {
            if (!damageDealt) {
                damageDealt = true;
                for (Entity e : handler.getScene().getEntities()) {
                    if (e instanceof Mob && Math.hypot(getCenterX() - e.getCenterX(), getCenterY() - e.getCenterY()) < EXPLOSION_RADIUS) {
                        ((Mob) e).hit(EXPLOSION_DAMAGE, new Force(getCenterX(), getCenterY(), e.getCenterX(), e.getCenterY(), EXPLOSION_KNOCKBACK, KNOCKBACK_DURATION));
                    }
                }
            }
            if (explosionTimer > 0) {
                g.setColor(new Color(255, 100, 0, (int) (255 * (explosionTimer / 120.0))));
                explosionTimer--;
                g.fillOval((int) ((x - EXPLOSION_RADIUS - handler.getCamera().xOffset()) * Assets.tileWidth),
                        (int) ((y - EXPLOSION_RADIUS - handler.getCamera().yOffset()) * Assets.tileHeight),
                        (int) (EXPLOSION_RADIUS * 2 * Assets.tileWidth), (int) (EXPLOSION_RADIUS * 2 * Assets.tileHeight));
            } else {
                destroy = true;
            }
        } else {
            if (exploding) {
                g.drawImage(Assets.explodingRusher, (int) ((x + (2f * Math.random() - 1f) / 8f - handler.getCamera().xOffset()) * Assets.tileWidth), (int) ((y + (2f * Math.random() - 1f) / 8f - handler.getCamera().yOffset()) * Assets.tileHeight), Assets.tileWidth, Assets.tileHeight, null);
            } else {
                g.drawImage(Assets.rusher, (int) ((x - handler.getCamera().xOffset()) * Assets.tileWidth), (int) ((y - handler.getCamera().yOffset()) * Assets.tileHeight), Assets.tileWidth, Assets.tileHeight, null);
            }
        }
    }
}
