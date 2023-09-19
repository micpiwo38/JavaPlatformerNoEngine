package gamestates;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods{

    //Tableau des boutons
    private MenuButton[] buttons = new MenuButton[3];
    //Le background du menu
    private BufferedImage backgroundIMG;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        //Appel de l'image depuis ma classe LoadSave
        backgroundIMG = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        //Lageur - Hauteur - POS X et Y
        menuWidth = (int) (backgroundIMG.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundIMG.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);
    }

    private void loadButtons() {
        //Play
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        //Options
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        //Quit
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton menu_button : buttons)
            menu_button.update();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(backgroundIMG, menuX, menuY, menuWidth, menuHeight, null);
        for(MenuButton menu_button : buttons)
            menu_button.draw(graphics);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton menu_button : buttons)
            //Menu recup isIn de la classe State
            if(isIn(e, menu_button)){
                menu_button.setMousePressed(true);
                System.out.println("Bouton cliquer");
                break;
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton menu_button : buttons){
            if(isIn(e,menu_button)){
                if(menu_button.isMousePressed())
                    menu_button.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton menu_button : buttons){
            menu_button.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton menu_button : buttons){
            menu_button.setMouseOver(false);
        }
        for(MenuButton menu_button : buttons)
            if(isIn(e, menu_button)){
                menu_button.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyboardPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            GameState.state = GameState.PLAYING;
    }

    @Override
    public void keyboardReleased(KeyEvent e) {

    }
}
