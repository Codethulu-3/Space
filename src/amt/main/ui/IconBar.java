package amt.main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author asilence24
 */
public class IconBar {
    
    private int maxNum, curNum;
    private int leftScreenPos, bottomScreenPos;
    private BufferedImage texture;
    
    /**
     * Makes a new bar
     * @param maxNum maximum size of the bar
     * @param leftScreenPos left most icon coordinate
     * @param bottomScreenPos bottom most icon coordinate
     * @param texture the texture of the icon to render
     */
    public IconBar(int maxNum, int leftScreenPos, int bottomScreenPos, BufferedImage texture){
        this.maxNum = maxNum;
        this.leftScreenPos = leftScreenPos;
        this.bottomScreenPos = bottomScreenPos;
        this.texture = texture;
    }
    
    public void update(int curNum){
        this.curNum = curNum;
    }
    
    public void render(Graphics g){
        for(int i = 0; i < curNum; i++){
            g.drawImage(texture, leftScreenPos + (texture.getWidth() * i), bottomScreenPos, null);
        }
    }
    
}
