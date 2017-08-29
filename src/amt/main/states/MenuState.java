package amt.main.states;

import amt.main.Handler;
import amt.main.gfx.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import amt.main.ui.Button;
import amt.main.ui.PauseMenu;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuState extends State {

    private int buttonWidth = 300, buttonHeight = 100;

    private Button startButton;
    private Button closeButton;

    private ArrayList<Button> buttons = new ArrayList<>();

    private int middle;

    private Handler handler;

    public MenuState(Handler handler) {
        super(handler);
        this.handler = handler;

        middle = (handler.getWidth() / 2) - (buttonWidth / 2);

        startButton = new Button(handler, Assets.buttonUp, Assets.buttonDown,
                middle, (handler.getHeight() / 4) - (buttonHeight / 2), buttonWidth, buttonHeight, "Start Game");
        closeButton = new Button(handler, Assets.buttonUp, Assets.buttonDown,
                middle, (handler.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight, "Exit");

        buttons.add(startButton);
        buttons.add(closeButton);
    }

    @Override
    public void update() {
        buttons.forEach((b) -> {
            b.update();
        });
        checkButtons();
    }

    /**
     * update button clicking
     */
    private void checkButtons() {
        if (startButton.click()) {
            State.setState(handler.getLevelSelectionState());
        }
        if (closeButton.click()) {
            handler.closeGame();
        }
    }

    @Override
    public void render(Graphics g) {
        buttons.forEach((b) -> {
            b.render(g);
        });

        //draws text
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        g.setColor(Color.white);
        
        g.drawString(startButton.getText(), (handler.getWidth() / 2) - (g.getFontMetrics().stringWidth(startButton.getText()) / 2), (handler.getHeight() / 4));
        g.drawString(closeButton.getText(), (handler.getWidth() / 2) - (g.getFontMetrics().stringWidth(closeButton.getText()) / 2), (handler.getHeight() / 2));
        //g.getFontMetrics().stringWidth(startButton.getText())
    }

    @Override
    public void reloadState() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(PauseMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setLevel(String level) {
    }

}
