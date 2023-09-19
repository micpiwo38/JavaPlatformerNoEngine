package Inputs;

import gamestates.GameState;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseClicked(mouseEvent);
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mousePressed(mouseEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mousePressed(mouseEvent);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseReleased(mouseEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseReleased(mouseEvent);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    //Clic droit + deplacement
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        switch (GameState.state){

            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseMoved(mouseEvent);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().mouseMoved(mouseEvent);
            }
        }
    }
}
