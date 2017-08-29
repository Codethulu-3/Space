/*Name:	Alex Silence
 *Date:
 *Period: 7
 *Teacher: Mrs. Krueger
 *Description:
 */
package amt.main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import amt.main.Handler;


public class Button {
    
    private Handler handler;
    private BufferedImage texture;
    private BufferedImage highlightTexture;
    private int x,y;
    private int width, height;
    private String text;
    
    private boolean highlighted=false;
    
    private boolean clickable=true;
    private BufferedImage inactiveTexture;
    
    public Button(Handler handler, BufferedImage texture, BufferedImage highlightTexture, int x, int y, int width, int height, String text){
        this.handler = handler;
        this.texture = texture;
        this.highlightTexture = highlightTexture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        clickable=true;
    }
    
    public Button(Handler handler, BufferedImage texture, BufferedImage highlightTexture, BufferedImage inactiveTexture, int x, int y, int width, int height, String text){
        this.handler = handler;
        this.texture = texture;
        this.highlightTexture = highlightTexture;
        this.inactiveTexture = inactiveTexture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
    
    public void update(){
        if(handler.getMouseManager().getMouseX() > x && handler.getMouseManager().getMouseX() < x + width){
            if(handler.getMouseManager().getMouseY() > y && handler.getMouseManager().getMouseY() < y + height){
                highlighted = true;
            } else {
                highlighted = false;
            }
        } else {
            highlighted = false;
        }
        
    }
    
    public void render(Graphics g){
        if (clickable==true) {
            if (!highlighted) {
                g.drawImage(texture, (int) x, (int) y, width, height, null);
            } else {
                g.drawImage(highlightTexture, (int) x, (int) y, width, height, null);
            }
        } else {
            g.drawImage(inactiveTexture, (int) x, (int) y, width, height, null);
        }
        /*
        if(highlighted){
            g.drawImage(highlightTexture, x, y, width, height, null);
        } else {
            g.drawImage(texture, x, y, width, height,null);
        }*/
    }
    
    public boolean click(){
        if(clickable==false){
            return false;
        }
        if(highlighted){
            if(handler.getMouseManager().getLeftPressed()){
                return true;
            }
        }
        return false;
    }
    
    //getters & setters
    public String getText(){return text;}
    public int getY(){return y;}
    
    public void setClickable(boolean clickable){this.clickable = clickable;}
}
