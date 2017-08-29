package amt.main.states;

import amt.main.Handler;
import amt.main.gfx.Assets;
import amt.main.ui.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asilence24
 */
public class LevelSelectionState extends State{
    
    private Handler handler;
    
    private int buttonWidth = 150, buttonHeight = 50;
    private ArrayList<Button> buttons = new ArrayList<>();
    
    private Button backButton;
    private Button rightArrow, leftArrow;
    private Button startButton;
    
    private int offset = 0;
    
    public LevelSelectionState(Handler handler){
        super(handler);
        this.handler = handler;
        
        int mid = (handler.getWidth() / 2) - (buttonWidth);
        
        backButton = new Button(handler, Assets.buttonUp, Assets.buttonDown,
                0, 0, buttonWidth, buttonHeight, "<- Back To Menu");
        rightArrow = new Button(handler, Assets.rightArrow, Assets.rightArrowDown,
                mid - 78, handler.getHeight() / 3, 78, 60, "");
        leftArrow = new Button(handler, Assets.leftArrow, Assets.leftArrowDown,
                handler.getWidth() / 2 + buttonWidth, handler.getHeight() / 3, 78, 60, "");
        startButton = new Button(handler, Assets.buttonUp, Assets.buttonDown, Assets.buttonGrey,
                mid, (int) (handler.getHeight() / 1.3) - (buttonHeight / 2), buttonWidth * 2, buttonHeight * 2, "Start Game");
        
        buttons.add(backButton);
        buttons.add(rightArrow);
        buttons.add(leftArrow);
        buttons.add(startButton);
    }
    
    @Override
    public void update() {
        buttons.forEach((b) -> {
            b.update();
        });
        buttons();
        
        if(offset == 1){
            startButton.setClickable(true);
            handler.getGameState().setLevel("test");
        } else if(offset == 2){
            startButton.setClickable(true);
            handler.getGameState().setLevel("Level2");
        } else {
            startButton.setClickable(false);
        }
    }
    
    private void buttons(){
        if(backButton.click()){
            State.setState(handler.getMenuState());
        }
        
        if(rightArrow.click()){
            if(offset >= 1){
                offset--;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(LevelSelectionState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(leftArrow.click()){
            if(offset <= 1){
                offset++;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(LevelSelectionState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(startButton.click()){
            State.setState(handler.getGameState());
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((handler.getWidth() / 2 - Assets.testWidth / 2) - (Assets.testWidth * offset), (handler.getHeight() / 2 - Assets.testHeight / 2) - 50, Assets.testWidth, Assets.testHeight);
        
        //levels
        g.drawImage(Assets.testLevel, (handler.getWidth() / 2 - Assets.testWidth / 2) - (Assets.testWidth * (offset - 1)), (handler.getHeight() / 2 - Assets.testHeight / 2) - 50, Assets.testWidth, Assets.testHeight, null);
        g.drawImage(Assets.level2, (handler.getWidth() / 2 - Assets.testWidth / 2) - (Assets.testWidth * (offset - 2)), (handler.getHeight() / 2 - Assets.testHeight / 2) - 50, Assets.testWidth, Assets.testHeight, null);
        
        //buttons
        buttons.forEach((b) -> {
            b.render(g);
        });
        
        //text
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        g.setColor(Color.white);
        
        g.drawString(backButton.getText(), 10, buttonHeight/2);
        g.drawString(startButton.getText(), (handler.getWidth() / 2) - (g.getFontMetrics().stringWidth(startButton.getText()) / 2), (int)(startButton.getY() + 50));
        
    }
    
    @Override
    public void reloadState() {
        offset=0;
    }

    @Override
    public void setLevel(String level) {
    }
    
}
