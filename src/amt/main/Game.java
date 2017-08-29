package amt.main;

import amt.main.gfx.Assets;
import amt.main.gfx.Display;
import amt.main.input.KeyManager;
import amt.main.input.MouseManager;
import amt.main.states.DeathState;
import amt.main.states.GameState;
import amt.main.states.LevelSelectionState;
import amt.main.states.MenuState;
import amt.main.states.State;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 *
 * @author asilence24
 */
public class Game extends GameLoop {
    
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    
    private int width,height;
    
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private Handler handler;
    
    private State menuState;
    private State gameState;
    private State levelSelectionState;
    private State deadState;
    
    /**
     * Is called on start of program
     */
    @Override
    public void startup() {
        width = 1280;
        height = 720;
        display = new Display("Game",width,height);
        
        //handler
        handler = new Handler(this);
        
        //loads textures
        Assets.init();
        
        //set up input
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        //set up state
        menuState = new MenuState(handler);
        gameState = new GameState(handler);
        levelSelectionState = new LevelSelectionState(handler);
        deadState = new DeathState(handler);
        
        State.setState(gameState);
        
        //set up closing operation
        display.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
    }
    
    /**
     * Is called to close the program
     */
    @Override
    public void shutdown() {
        System.exit(0);//temp code to stop game
    }
    
    /**
     * Runs non graphics processes
     */
    @Override
    public void update() {
        State.getState().update();
        keyManager.update();
    }
    
    /**
     * Runs visual process
     */
    @Override
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        
        draw(g);
        
        bs.show();
        g.dispose();
    }
    
    /**
     * Is called in render, for organization
     * @param g Current Graphics component
     */
    private void draw(Graphics g){
        g.clipRect(0, 0, width, height);
        State.getState().render(g);
    }
    
    /**
     * To shutdown the program correctly
     */
    public void closeGame(){stop();}
    
    //Getters
    public KeyManager getKeyManager(){return keyManager;}
    public MouseManager getMouseManager(){return mouseManager;}
    
    public State getMenuState(){return menuState;}
    public State getGameState(){return gameState;}
    public State getLevelSelectionState(){return levelSelectionState;}
    public State getDeathState(){return deadState;}
    
    public int getWidth(){return width;}
    public int getHeight(){return height;}
}
