package amt.main.entities;

import amt.main.Handler;
import amt.main.gfx.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    
    protected float x, y;
    protected Handler handler;
    protected Rectangle bounds;
    protected boolean destroy = false; //If true, the level will remove this from the entity list ASAP.
    
    protected float xBound, yBound, boundWidth, boundHeight;
    
    public Entity(float x, float y, Rectangle bounds, Handler handler) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.bounds = bounds;
        
        //Convert bounds coordinates, which are in pixels, to tiles coordinates.
        xBound = (float)bounds.x / Assets.tileWidth;
        yBound = (float)bounds.y / Assets.tileHeight;
        boundWidth = (float)bounds.width / Assets.tileWidth;
        boundHeight = (float)bounds.height / Assets.tileHeight;
    }
    
    public abstract void update();
    
    public abstract void render(Graphics g);
    
    protected boolean collisionWithTile(float x, float y) {
        return handler.getScene().getTile(x, y).isSolid();
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getCenterX() {
        return x + .5f;
    }
    
    public float getCenterY() {
        return y + .5f;
    }
    
    public boolean destroy() {
        return destroy;
    }
    
    /**
     * @return The bounds of the entity, in pixels but in the level worldspace. Just use this for intersections.
     */
    public Rectangle getBounds() {
        return new Rectangle((int)(x * Assets.tileWidth) + bounds.x, (int)(y * Assets.tileHeight) + bounds.y, bounds.width, bounds.height);
    }
}
