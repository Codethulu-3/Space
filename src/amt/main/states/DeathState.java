package amt.main.states;

import amt.main.Handler;
import amt.main.gfx.Assets;
import amt.main.ui.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author asilence24
 */
public class DeathState extends State {
    
    private Handler handler;
    
    private int buttonWidth = 300, buttonHeight = 100;
    
    private ArrayList<Button> buttons = new ArrayList<>();
    private Button restartButton;
    private Button mainMenuButton;
    
    private int i;
    
    private String[] death = {"You ded Fam", "You tryed", "beter luck next tim", "A for effort", "participation Award", "brilliant", "esports ready",
    "best player NA", "6/10 \"too much water\" -IGN"};
    
    public DeathState(Handler handler){
        super(handler);
        
        this.handler = handler;
        
        restartButton = new Button(handler, Assets.buttonUp, Assets.buttonDown,
                handler.getWidth()/4, (int) (handler.getHeight() / 1.2), buttonWidth, buttonHeight, "Replay Level");
        mainMenuButton = new Button(handler, Assets.buttonUp, Assets.buttonDown,
                handler.getWidth()/2, (int) (handler.getHeight() / 1.2), buttonWidth, buttonHeight, "Back To Menu");
        
        buttons.add(restartButton);
        buttons.add(mainMenuButton);
    }
    
    @Override
    public void update() {
        buttons.forEach((b) -> {
            b.update();
        });
        
        if(restartButton.click()){
            handler.getGameState().setLevel("test");
            State.setState(handler.getGameState());
        }
        if(mainMenuButton.click()){
            State.setState(handler.getMenuState());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        
        buttons.forEach((b) -> {
            b.render(g);
        });
        
        
        
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        
        g.drawString(death[i], (handler.getWidth() / 2) - (g.getFontMetrics().stringWidth(death[i]) / 2), handler.getHeight()/2);
        
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        
        g.drawString(restartButton.getText(), (handler.getWidth() / 4) - (g.getFontMetrics().stringWidth(restartButton.getText()) / 2) + buttonWidth/2, (int)(restartButton.getY() + 50));
        g.drawString(mainMenuButton.getText(), ((handler.getWidth() / 2) - (g.getFontMetrics().stringWidth(mainMenuButton.getText()) / 2)) + buttonWidth/2, (int)(mainMenuButton.getY() + 50));
    }
    
    @Override
    public void reloadState() {
        Random r = new Random();
        i = r.nextInt(death.length);
    }
    @Override
    public void setLevel(String level) {}
    
}
