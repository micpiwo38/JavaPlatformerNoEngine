package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

//Import classe static player action
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Direction.*;

public class GamePanel extends JPanel {
    //Cette classe implemente mouseListener + mouseMotionListener (clic + déplacement)
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    //Chager la plache de sprites = sprite Atlas
    private BufferedImage img;
    private BufferedImage[][] animation;
    //Animation
    private int animationTick, animationIndex, animationSpeed = 15;

    //Player Action
    private int playerAction = IDLE;
    private int playerDirection = -1;

    private boolean moving = false;


    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        //Import du sprite player
        importImg();
        //Animation
        loadAnimation();
        
        setPanelSize();
        //Input = instance de la classe KeyboardInput qui implements KeyListener interface
        addKeyListener(new KeyboardInputs(this));
        //Souris
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimation() {
        //animation est un tableau a 2 dimenssion
        //verticale contient 9 sprites
        /*
            * *
            * * * * *
            * * *
            * * * *
            * *
            * *
            *
            * *
            *
         */
        //Verticale contient 9 sprites et horizontale contient au max 5 sprites
        animation = new BufferedImage[9][5];
        //Boucle de parcours du tableau verticale
        for(int j = 0; j < animation.length; j++)
            //Boucle de parcours horizontale
            for(int i = 0; i < animation[j].length; i++)
                //en Horizontale 0*64 => 1*64 => etc...
                //en verticale 0*40 => 1*40 => etc...
                animation[j][i] = img.getSubimage(i*64, j * 40, 64, 40);
    }

    private void importImg(){
        //La planche possède des cases de 64 * 40
        //Charger la planche de sprite player 32 * 32 px au centre des cases
        InputStream is = getClass().getResourceAsStream("res/player_sprites.png");
        try{
            assert is != null;
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                //Liberer la RAM
                assert is != null;
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setPreferredSize(size);
    }
    public void setDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }
    private void updateAnimationTick() {

        //Incremente
        animationTick++;
        //quand anim tick arrive a 30
        if(animationTick >= animationSpeed){
            //On reset
            animationTick = 0;
            //On change d'index dans le tableau de sprite
            animationIndex++;
            //Si l'index depasse la taille du tableau 0 soit : idleAnimation[2]
            if(animationIndex >= GetSpriteAmount(playerAction))
                //On reset l'index => on retourne au sprite 0 0
                animationIndex = 0;
            //La boule passe de 0 => 1 toutes les 0.3s
        }

    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePosition() {
        if(moving){
            switch(playerDirection){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }


    //La souris deplace le rect

    public void paintComponent(Graphics graphics){
        //Contructeur parent = jPanel
        super.paintComponent(graphics);

        updateAnimationTick();
        setAnimation();
        updatePosition();

        graphics.drawImage(animation[playerAction][animationIndex], (int) xDelta, (int) yDelta,256, 160, null);
        System.out.println(xDelta);
    }
}
