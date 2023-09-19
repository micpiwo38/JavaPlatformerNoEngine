package Inputs;

import gamestates.GameState;
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
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().keyboardPressed(keyEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().keyboardPressed(keyEvent);
            }
        }
    }

    @Override
    //Touche relachée
    public void keyReleased(KeyEvent keyEvent) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().keyboardReleased(keyEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().keyboardReleased(keyEvent);
            }
        }
    }
}
