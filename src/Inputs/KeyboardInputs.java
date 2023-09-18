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
            //GAUCHE
            case KeyEvent.VK_Q:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            //DROITE
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
            //BAS
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            //HAUT
            case KeyEvent.VK_Z:
                gamePanel.getGame().getPlayer().setUp(true);
                break;
        }
    }

    @Override
    //Touche relachée
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            //GAUCHE
            case KeyEvent.VK_Q:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            //DROITE
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
            //BAS
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
                break;
            //HAUT
            case KeyEvent.VK_Z:
                gamePanel.getGame().getPlayer().setUp(false);
                break;
        }
    }
}
