package entities;

import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Direction.*;
import static utils.Constants.Direction.DOWN;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    //Variables
    //Tableau 2 dimmension de Atlas Sprite
    private BufferedImage[][] animation;
    //Animation
    private int animationTick, animationIndex, animationSpeed = 25;
    //Player Action
    private int playerAction = IDLE;
    //Les touches clavier
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

    private boolean moving = false, attacking = false;

    //Constructeur
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
    }

    //Methodes = Mise a jour
    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics graphics){
        graphics.drawImage(animation[playerAction][animationIndex], (int) x, (int) y,width, height, null);
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
            if(animationIndex >= GetSpriteAmount(playerAction)){
                //On reset l'index => on retourne au sprite 0 0
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {

        int start_animation = playerAction;
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(attacking)
            playerAction = ATTACK;

        if(start_animation != playerAction)
            resetAnimationTick();

    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    //Deplacement du player = clavier
    private void updatePosition() {
        //Par defaut IDLE
        moving = false;

        //Horizontale
        if(left && !right){
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        //Verticale
        if(up && !down){
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }


    //Animations
    private void loadAnimation() {
            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
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

    //Stopper le player en cas de perte du focus
    public void resetBooleansDirection(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
