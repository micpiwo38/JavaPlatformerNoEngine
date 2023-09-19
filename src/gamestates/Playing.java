package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods{
    private Player player;
    //Levels 1
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(100,200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());

    }

    //Quand on quit le focus du jeux
    public void  windowFocusLost(){
        player.resetBooleansDirection();
    }

    //GETTER SETTER PLAYER CLASSE
    public Player getPlayer(){
        return player;
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics graphics) {
        levelManager.draw(graphics);
        player.render(graphics);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            player.setAttacking(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyboardPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            //GAUCHE
            case KeyEvent.VK_Q:
                player.setLeft(true);
                break;
            //DROITE
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            //BAS
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            //HAUT
            case KeyEvent.VK_Z:
                player.setUp(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
                break;
        }
    }

    @Override
    public void keyboardReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            //GAUCHE
            case KeyEvent.VK_Q:
                player.setLeft(false);
                break;
            //DROITE
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            //BAS
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            //HAUT
            case KeyEvent.VK_Z:
                player.setUp(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);

        }
    }
}
