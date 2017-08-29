package amt.main.scene;

import amt.main.Handler;
import amt.main.entities.Entity;
import amt.main.entities.Player;
import amt.main.gfx.Assets;
import amt.main.gfx.Camera;
import amt.main.tiles.Tile;
import java.awt.Graphics;
import java.util.HashSet;

/**
 *
 * @author mgalan11
 */
public class Scene {
    
    Tile[][] tiles;
    HashSet<Entity> entities, oldEntities, newEntities;
    private Camera camera;
    private Player player;
    private int shakeTimer = 0;
    private int shakeAmount = 0;
    
    private Handler handler;
    private SceneGenerator sg = new SceneGenerator();
    
    public Scene(int width, int height, Handler handler) {
        tiles = new Tile[width][height];
        entities = new HashSet<>();
        oldEntities = new HashSet<>();
        newEntities = new HashSet<>();
        camera = new Camera(handler);
        handler.setCamera(camera);
        this.handler = handler;
        //sg.generateMap();
    }
    
    public void update(){
        entities.addAll(newEntities);
        newEntities.clear();
        for (Entity e : entities) {
            e.update();
            if (e.destroy())
                oldEntities.add(e);
        }
        entities.removeAll(oldEntities);
        oldEntities.clear();
        camera.updateCamera();
        if (shakeTimer > 0) {
            shakeTimer--;
            shakeAmount --;
        }
    }
    
    public void render(Graphics g) {
        if (shakeTimer > 0) {
            g.translate((int)(2 * shakeAmount * Math.random() - shakeAmount), (int)(2 * shakeAmount * Math.random() - shakeAmount));
        } else {
            g.translate(0, 0);
        }
        
        renderMap(g);
        
        for (Entity e : entities) {
            e.render(g);
        } 
    }
    
    private void renderMap(Graphics g){
        int xEnd;
        int yEnd;
        if((int)(camera.xOffset() + handler.getWidth() / Assets.tileWidth) + 1 > tiles.length){
            xEnd = tiles.length;
        } else {
            xEnd = (int)(camera.xOffset() + handler.getWidth() / Assets.tileWidth) + 1;
        }
        if((int)(camera.yOffset() + handler.getHeight() / Assets.tileHeight) + 2 > tiles[0].length){
            yEnd = tiles[0].length;
        } else {
            yEnd = (int)(camera.yOffset() + handler.getHeight() / Assets.tileHeight) + 2;
        }
        for (int x = (int)camera.xOffset(); x < xEnd; x++) {
            for (int y = (int)camera.yOffset(); y < yEnd; y++) {
                if(tiles[x][y] != null){
                    tiles[x][y].render(x, y, g);
                }
            }
        }
    }
    
    public void screenShake(int amount, int length) {
        shakeTimer = length;
        shakeAmount = amount;
    }
    
    /**
     * Cast a ray between two points to see if the path of the line is clear.
     * @param x1 The x coordinate of the first target.
     * @param y1 The y coordinate of the first target.
     * @param x2 The x coordinate of the second target.
     * @param y2 The y coordinate of the second target.
     * @return True if the raycast successfully connected the points. (No obstruction). False otherwise.
     */
    public boolean raycast(float x1, float y1, float x2, float y2) {
        float length = (float)Math.hypot(x1 - x2, y1 - y2);
        int checks = (int)(length / .1f);
        float xStep = (x2 - x1) / checks;
        float yStep = (y2 - y1) / checks;
        for (int i = 0; i < checks; i++) {
            if (getTile(x1, y1).isSolid()) {
                return false;
            }
            x1 += xStep;
            y1 += yStep;
        }
        return true;
    }
    
    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }
    
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
    
    public Tile getTile(float x, float y) {
        return tiles[(int)x][(int)y];
    }
    
    public void addEntity(Entity entity) {
        if (entity instanceof Player) {
            player = (Player) entity;
            camera.centerOnEntity(player);
        }
        newEntities.add(entity);
    }
    
    /** @return The width of the level, in tiles. */
    public int getWidth() {return tiles.length;}
    /** @return The height of the level, in tiles. */
    public int getHeight() {return tiles[0].length;}
    public HashSet<Entity> getEntities() {return entities;}
    public Player getPlayer() {return player;}
}
