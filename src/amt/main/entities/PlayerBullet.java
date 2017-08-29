package amt.main.entities;

import amt.main.Handler;
import amt.main.gfx.Assets;
import amt.main.util.Force;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends Projectile {

    private final int DAMAGE = 1;
    
    private int size;
    
    public PlayerBullet(float x, float y, int size, float xMove, float yMove, Handler handler) {
        super(.2f, x - (size * .2f / 2), y - (size * .2f / 2), xMove, yMove, new Rectangle((int)((.9f + .1f * size) * 20), (int)((.9f + .1f * size) * 20), (int)((.9f + .1f * size) * 20), (int)((.9f + .1f * size) * 20)), handler);
        this.size = size;
    }

    @Override
    public void render(Graphics g) {
        float mult = 1f + .2f * (size - 1);
        g.drawImage(Assets.bullet, (int)((x - handler.getCamera().xOffset()) * Assets.tileWidth), (int)((y - handler.getCamera().yOffset()) * Assets.tileHeight),
                (int)(Assets.tileWidth * mult), (int)(Assets.tileHeight * mult), null);
    }
    
    @Override
    protected void checkEntityCollisions() {
        for (Entity e : handler.getScene().getEntities()) {
            if (e != handler.getScene().getPlayer() && e instanceof Mob && getBounds().intersects(e.getBounds())) {
                float magnitude = (float) Math.hypot(xMove, yMove);
                ((Mob)e).hit(DAMAGE * size, new Force(xMove * knockback / magnitude, yMove * knockback / magnitude, 60));
                destroy = true;
            }
        }
    }
}
