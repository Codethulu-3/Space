package amt.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseManager implements MouseListener, MouseMotionListener{
    
    private int mouseX,mouseY;
    private boolean leftPressed,rightPressed;
    
    @Override
    public void mouseClicked(MouseEvent e){
        
    }

    @Override
    public void mouseEntered(MouseEvent e){
        
    }

    @Override
    public void mouseExited(MouseEvent e){
        
    }

    @Override
    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        
    }

    @Override
    public void mouseMoved(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
    //Getters
    public int getMouseX(){return mouseX;}
    public int getMouseY(){return mouseY;}
    public boolean getLeftPressed(){return leftPressed;}
    public boolean getRightPressed(){return rightPressed;}
}
