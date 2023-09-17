package Inputs;

import main.Game;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Direction.*;

//Implemente KeyListener INTERFACE
public class KeyboardInputs implements KeyListener {

    //Le game panel
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            //La gauche
            case KeyEvent.VK_Q:
                //System.out.println("La gauche");
                gamePanel.setDirection(LEFT);
                break;
            //La droite
            case KeyEvent.VK_D:
                //System.out.println("La droite");
                gamePanel.setDirection(RIGHT);
                break;
            //Le bas
            case KeyEvent.VK_S:
                //System.out.println("Le bas");
                gamePanel.setDirection(DOWN);
                break;
            //Haut
            case KeyEvent.VK_Z:
                //System.out.println("Le haut");
                gamePanel.setDirection(UP);
                break;

        }
    }

    @Override
    //Touche relachée
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_Q:
            case KeyEvent.VK_D:
            case KeyEvent.VK_S:
            case KeyEvent.VK_Z:
                gamePanel.setMoving(false);
                break;
        }
    }
}
