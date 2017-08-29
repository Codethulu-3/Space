package amt.main.entities;

import amt.main.Handler;
import amt.main.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Turret extends Mob {
    //Stuff you might want to tweak:
    private static final float RANGE = 10f; //Range, in blocks, that the turret can detect the player
    private static final long FIRE_RATE = 1500;           //Milliseconds between shooting
    private static final long WARNING_TIME = 500;         //Milliseconds before shooting that the laser stops tracking the player
    //Stuff you don't need to touch:
    private long lastFire, timeSinceLastShot;
    private boolean loaded, shooting, seen;
    private float lastKnownCenterX, lastKnownCenterY, lastKnownX, lastKnownY;
    private Color laserColor, indicatorColor;

    public Turret(float x, float y, Handler handler) {
        super(x, y, 3, 0, new Rectangle(0, 0, 64, 64), handler);
    }

    @Override
    public void update() {
        float distance = (float) Math.hypot(handler.getScene().getPlayer().getCenterX() - getCenterX(), 
                handler.getScene().getPlayer().getCenterY() - getCenterY());
        timeSinceLastShot = System.currentTimeMillis() - lastFire;
        if (timeSinceLastShot > FIRE_RATE) {
            loaded = true;
        }
        //We can see the player
        if (distance < RANGE && handler.getScene().raycast(getCenterX(), getCenterY(), 
                handler.getScene().getPlayer().getCenterX(), handler.getScene().getPlayer().getCenterY())) {
            seen = true;
            laserColor = Color.RED;
            if (!shooting) {
                //Update our known location of him
                lastKnownCenterX = handler.getScene().getPlayer().getCenterX();
                lastKnownCenterY = handler.getScene().getPlayer().getCenterY();
                lastKnownX = handler.getScene().getPlayer().getX();
                lastKnownY = handler.getScene().getPlayer().getY();
            }
            //If we're within the warning time - begin firing
            if (timeSinceLastShot > FIRE_RATE - WARNING_TIME) {
                laserColor = Color.ORANGE;
                shooting = true;
            }   
        } else {
            seen = false;
        }
        if (loaded && shooting) {
            //Actually shoot
            float lastDistance = (float) Math.hypot(lastKnownCenterX - x, lastKnownCenterY - y);
            handler.getScene().addEntity(new TurretBullet(x, y, .1f * (lastKnownX - x) / lastDistance, .1f * (lastKnownY - y) / lastDistance, handler));
            lastFire = System.currentTimeMillis();
            shooting = false;
            loaded = false;
        }
        if (loaded) {
            indicatorColor = Color.GREEN;
        } else {
            indicatorColor = Color.DARK_GRAY;
        }
        if (shooting) {
            laserColor = Color.ORANGE;
            indicatorColor = Color.ORANGE;
        }   
    }

    @Override
    public void render(Graphics g) {
        /* Image rotation code:
        Graphics2D g2d = (Graphics2D) g; // Create a Java2D version of g.
        g2d.translate((int)((x - handler.getCamera().xOffset()) * Assets.tileWidth), (int)((y - handler.getCamera().yOffset()) * Assets.tileHeight));
        g2d.rotate(1);  // Rotate the image by 1 radian.
        g2d.drawImage(Assets.turret, 0, 0, Assets.tileWidth, Assets.tileHeight, null);
        g2d.rotate(-1);
        g2d.translate(-(int)((x - handler.getCamera().xOffset()) * Assets.tileWidth), -(int)((y - handler.getCamera().yOffset()) * Assets.tileHeight));
         */
        if (seen || shooting) {
            //Draw the laser
            g.setColor(laserColor);
            g.drawLine((int) ((getCenterX() - handler.getCamera().xOffset()) * Assets.tileWidth),
                    (int) ((getCenterY() - handler.getCamera().yOffset()) * Assets.tileHeight),
                    (int) ((lastKnownCenterX - handler.getCamera().xOffset()) * Assets.tileWidth),
                    (int) ((lastKnownCenterY - handler.getCamera().yOffset()) * Assets.tileHeight));
        }
        //Draw the turret
        g.drawImage(Assets.turret, (int) ((getX() - handler.getCamera().xOffset()) * Assets.tileWidth),
                (int) ((getY() - handler.getCamera().yOffset()) * Assets.tileHeight), Assets.tileWidth, Assets.tileHeight, null);
        //Draw the indicator light
        g.setColor(indicatorColor);
        g.fillRect((int) ((getX() - handler.getCamera().xOffset()) * Assets.tileWidth), (int) ((getY() - handler.getCamera().yOffset()) * Assets.tileHeight), 15, 15);
    }
}
