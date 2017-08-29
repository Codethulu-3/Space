package amt.main.states;

import amt.main.Handler;
import amt.main.scene.Scene;
import amt.main.scene.SceneLoader;
import amt.main.ui.PauseMenu;
import java.awt.Graphics;


public class GameState extends State{
    
    private Handler handler;
    private Scene scene;
    
    private boolean paused=false;
    private PauseMenu pauseMenu;
    
    private String sceneName;
    
    public static String curLevel;
    
    public GameState(Handler handler){    
        super(handler);
        //temp
        scene = SceneLoader.loadScene("world", handler);
        handler.setScene(scene);
        pauseMenu = new PauseMenu(handler);
        this.handler = handler;
        
    }
    
    @Override
    public void update(){
        if(!paused){
            scene.update();
        } else {
            pauseMenu.update();
        }
        paused = pauseMenu.checkPaused();
    }

    @Override
    public void render(Graphics g){
        scene.render(g);
        if(paused){
            pauseMenu.render(g);
        }
    }

    @Override
    public void reloadState() {
        handler.getKeyManager().escapePressed=false;
        paused = false;
    }
    
    @Override
    public void setLevel(String levelName){
        scene = SceneLoader.loadScene(levelName, handler);
        handler.setScene(scene);
    }
    
}
