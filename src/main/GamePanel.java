package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;


import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;


public class GamePanel extends JPanel {
    //Cette classe implemente mouseListener + mouseMotionListener (clic + déplacement)
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){

        mouseInputs = new MouseInputs(this);
        this.game = game;


        setPanelSize();
        //Input = instance de la classe KeyboardInput qui implements KeyListener interface
        addKeyListener(new KeyboardInputs(this));
        //Souris
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        //System.out.println("WIDTH : " + GAME_WIDTH + " HEIGHT : " + GAME_HEIGHT);
    }


    //GETTER SETTER GAME CLASSE
    public Game getGame(){
        return game;
    }

    public void paintComponent(Graphics graphics){
        //Contructeur parent = jPanel
        super.paintComponent(graphics);
        game.render(graphics);

    }
}
