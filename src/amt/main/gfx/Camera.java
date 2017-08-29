package amt.main.gfx;

import amt.main.Handler;
import amt.main.entities.Entity;

/**
 *
 * @author asilence24
 */
public class Camera {
    
    private Handler handler;
    private float xOffset, yOffset;
    private Entity centeredEntity;
    
    public Camera(Handler handler) {
        this.handler = handler;
    }
    
    /**
     * The camera will now center on e.
     * @param e The entity to center on.
     */
    public void centerOnEntity(Entity e){
        centeredEntity = e;
    }
    
    /**
     * Centers the camera on the centeredEntity.
     */
    public void updateCamera(){
        xOffset = centeredEntity.getX() - handler.getWidth() / Assets.tileWidth / 2;
        yOffset = centeredEntity.getY() - handler.getHeight() / Assets.tileHeight / 2;
        if (xOffset < 0) {
            xOffset = 0;
        }
        if (xOffset + handler.getWidth() / Assets.tileWidth > handler.getScene().getWidth()) {
            xOffset = handler.getScene().getWidth() - handler.getWidth() / Assets.tileWidth;
        }
        if (yOffset < 0) {
            yOffset = 0;
        }
        if (yOffset + handler.getHeight() / Assets.tileHeight > handler.getScene().getHeight()) {
            yOffset = handler.getScene().getHeight() - handler.getHeight() / Assets.tileHeight;
        }
    }
    
    /**
     * @return The x position of the top left corner of the camera, in tiles.
     */
    public float xOffset(){return xOffset;}
    /**
     * @return The y position of the top left corner of the camera
     */
    public float yOffset(){return yOffset;}
}

