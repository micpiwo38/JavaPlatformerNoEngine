package entities;

import main.Game;
import utils.LoadSave;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.CanMoveHere;

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
    //Vitesse du player
    private float playerSpeed = 2.0f;
    //Condition bool bouger et attaquer
    private boolean moving = false, attacking = false;

    //Tableau 2D les coordonnées du niveau colonnes et lignes
    private int[][] lvlData;

    //Offset de la hitbox du player soit une tuile de 60 * 40
    //Dans cette tuile le player est positioner a 20 en x et 28 en y
    //TOP LEFT = 0,0
    //Et la tete du srite a 21
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;


    //Constructeur
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        //Charge l'animation de base
        loadAnimation();
        //Dans la tuile de 64 * 40 => le player est positioner a 20 en x et 28 en y
        //La hitbox du player = pos x, pos y, 20 * 2 et 28 * 2
        initHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
    }

    //Methodes = Mise a jour
    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics graphics){
        //Pour afficher le player = position de la hitbox = 64 - offset = 42 => 22
        //en y => position de la hitbox = 40 - offset = 8 => 32
        graphics.drawImage(animation[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),width, height, null);
        drawHitbox(graphics);
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
        //Par defaut ANIMATION = IDLE et le player ne bouge pas
        moving = false;

        //Si on appuie sur rien => on retourne rien
        if(!left && !right && !up && !down)
            return;

        //Vitesse temporaire
        float xSpeed = 0, ySpeed = 0;

        //Horizontale
        if(left && !right)
            xSpeed = -playerSpeed;
        else if (right && !left)
            xSpeed = playerSpeed;


        //Verticale
        if(up && !down)
            ySpeed = -playerSpeed;
        else if (down && !up)
            ySpeed = playerSpeed;

        //Si on peu bouger = la hitbox X + la vitesse, idem en y et la hitbox largeur et hauteur et le tableau du niveau
        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
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

    //Charger les données du niveau dans un tableau 2D et check les collisions avec la position du player
    public void loadLevelData(int[][] lvlData){
        this.lvlData = lvlData;
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
