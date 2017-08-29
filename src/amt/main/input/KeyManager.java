package amt.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author asilence24
 */
public class KeyManager implements KeyListener{
    
    private boolean[] oldKeys, curKeys;
    public boolean escapePressed=false;
    
    public KeyManager(){
        oldKeys = new boolean[256];
        curKeys = new boolean[256];
    }
    
    public void update(){
        oldKeys = curKeys.clone();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        curKeys[e.getKeyCode()] = true;
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            escapePressed=!escapePressed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        curKeys[e.getKeyCode()] = false;
    }
    
    public boolean getWPressed(){return curKeys[KeyEvent.VK_W];}
    public boolean getWTapped() {
        return curKeys[KeyEvent.VK_W] && curKeys[KeyEvent.VK_W] != oldKeys[KeyEvent.VK_W];
    }
    
    public boolean getAPressed(){return curKeys[KeyEvent.VK_A];}
    public boolean getATapped() {
        return curKeys[KeyEvent.VK_A] && curKeys[KeyEvent.VK_A] != oldKeys[KeyEvent.VK_A];
    }
    
    public boolean getSPressed(){return curKeys[KeyEvent.VK_S];}
    public boolean getSTapped() {
        return curKeys[KeyEvent.VK_S] && curKeys[KeyEvent.VK_S] != oldKeys[KeyEvent.VK_S];
    }
    
    public boolean getDPressed(){return curKeys[KeyEvent.VK_D];}
    public boolean getDTapped() {
        return curKeys[KeyEvent.VK_D] && curKeys[KeyEvent.VK_D] != oldKeys[KeyEvent.VK_D];
    }
    
    public boolean getUpPressed(){return curKeys[KeyEvent.VK_UP];}
    public boolean getUpTapped() {
        return curKeys[KeyEvent.VK_UP] && curKeys[KeyEvent.VK_UP] != oldKeys[KeyEvent.VK_UP];
    }
    public boolean getUpReleased() {
        return !curKeys[KeyEvent.VK_UP] && curKeys[KeyEvent.VK_UP] != oldKeys[KeyEvent.VK_UP];
    }
    
    public boolean getDownPressed(){return curKeys[KeyEvent.VK_DOWN];}
    public boolean getDownTapped() {
        return curKeys[KeyEvent.VK_DOWN] && curKeys[KeyEvent.VK_DOWN] != oldKeys[KeyEvent.VK_DOWN];
    }
    public boolean getDownReleased() {
        return !curKeys[KeyEvent.VK_DOWN] && curKeys[KeyEvent.VK_DOWN] != oldKeys[KeyEvent.VK_DOWN];
    }
    
    public boolean getLeftPressed(){return curKeys[KeyEvent.VK_LEFT];}
    public boolean getLeftTapped() {
        return curKeys[KeyEvent.VK_LEFT] && curKeys[KeyEvent.VK_LEFT] != oldKeys[KeyEvent.VK_LEFT];
    }
    public boolean getLeftReleased() {
        return !curKeys[KeyEvent.VK_LEFT] && curKeys[KeyEvent.VK_LEFT] != oldKeys[KeyEvent.VK_LEFT];
    }
    
    public boolean getRightPressed(){return curKeys[KeyEvent.VK_RIGHT];}
    public boolean getRightTapped() {
        return curKeys[KeyEvent.VK_RIGHT] && curKeys[KeyEvent.VK_RIGHT] != oldKeys[KeyEvent.VK_RIGHT];
    }
    public boolean getRightReleased() {
        return !curKeys[KeyEvent.VK_RIGHT] && curKeys[KeyEvent.VK_RIGHT] != oldKeys[KeyEvent.VK_RIGHT];
    }
    
    public boolean getSpacePressed(){return curKeys[KeyEvent.VK_SPACE];}
    public boolean getSpaceTapped() {
        return curKeys[KeyEvent.VK_SPACE] && curKeys[KeyEvent.VK_SPACE] != oldKeys[KeyEvent.VK_SPACE];
    }
}
