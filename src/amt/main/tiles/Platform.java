/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amt.main.tiles;

import amt.main.Handler;
import amt.main.gfx.Assets;
import java.awt.Graphics;

public class Platform extends Tile {
    
    public Platform(Handler handler) {
        super(handler);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void render(float x, float y, Graphics g) {
        g.drawImage(Assets.platform, (int)((x - handler.getCamera().xOffset()) * Assets.tileWidth), (int)((y - handler.getCamera().yOffset()) * Assets.tileHeight), null);
    }
}
