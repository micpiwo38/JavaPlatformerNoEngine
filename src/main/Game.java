package main;

import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    //Frame per seconds
    private final int FPS_SET = 120;
    //UPS = update per second
    private final int UPS_SET = 200;



    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);

    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private Playing playing;
    private Menu menu;




    //Construteur
    public Game(){
        //Init de la classe player
        initClasses();
        //Cree la panneau
        gamePanel = new GamePanel(this);
        //On l'ajoute a la fenetre
        gameWindow = new GameWindow(gamePanel);
        //Track les inputs clavier souris
        gamePanel.requestFocus();

        //Boucle infinie
        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch (GameState.state){
            case PLAYING -> {
                playing.update();
            }
            case MENU -> {
                menu.update();
            }
            case OPTIONS, QUIT -> {
                System.exit(0);
            }
        }
    }

    //Le render
    public void render(Graphics graphics){


        switch (GameState.state){

            case PLAYING -> {
                playing.draw(graphics);
            }
            case MENU -> {
                menu.draw(graphics);
            }
        }


    }

    @Override
    //Game Thread
    public void run() {
        //nano secondes = 1 et 9 zero / 120
        double time_per_frame = 1000000000.0 / FPS_SET;
        double time_per_update = 1000000000.0 / UPS_SET;
        long previous_time = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long last_check = System.currentTimeMillis();
        double delta_u = 0;
        double delta_f = 0;

        //Game loop
        while (true){
            //Boucle infinie

            long current_time = System.nanoTime();

            //deta U sera 1.0 ou + quand la durée depuis la dernière mise a jour est = ou plus grande que time_per_update
            delta_u += (current_time - previous_time) / time_per_update;
            delta_f += (current_time - previous_time) / time_per_frame;

            previous_time = current_time;

            if(delta_u >= 1){
                update();
                updates++;
                delta_u--;
            }

            if(delta_f >= 1){
                //Refresh = effacer et repaint a chaque frame
                gamePanel.repaint();
                frames++;
                delta_f--;
            }

            //Compter les frames

            if(System.currentTimeMillis() - last_check >= 1000){
                last_check = System.currentTimeMillis();
                System.out.println("FPS :" + frames + " | UPS " + updates);
                frames = 0;
                updates = 0;
            }

        }
    }

    //Quand on quit le focus du jeux
    public void  windowFocusLost(){
        if(GameState.state == GameState.PLAYING)
            playing.getPlayer().resetBooleansDirection();
    }

    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }


}
