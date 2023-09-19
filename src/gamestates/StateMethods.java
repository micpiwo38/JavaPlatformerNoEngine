package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    //PROTOTYPES
    public void update();
    public void draw(Graphics graphics);

    //SOURIS
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);

    //CLAVIER
    public void keyboardPressed(KeyEvent e);
    public void keyboardReleased(KeyEvent e);
}
