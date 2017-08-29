/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amt.main.tiles;

import amt.main.Handler;
import java.awt.Graphics;

public abstract class Tile {
    
    protected Handler handler;
    
    public Tile(Handler handler) {
        this.handler = handler;
    }
    
    /**
     * Render the object to the screen.
     * @param x IN TILES
     * @param y IN TILES
     * @param g Graphics
     */
    public abstract void render(float x, float y, Graphics g);
    
    /** @return If the tile can not be passed through
     */
    public abstract boolean isSolid();
}
