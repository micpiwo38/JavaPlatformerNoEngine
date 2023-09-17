package main;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    //Construteur
    public Game(){
        //Cree la panneau
        gamePanel = new GamePanel();
        //On l'ajoute a la fenetre
        gameWindow = new GameWindow(gamePanel);
        //Tack les inputs clavier souris
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    //Game Thread
    public void run() {
        //nano secondes = 1 et 9 zero / 120
        double time_per_frame = 1000000000.0 / FPS_SET;
        //Dernière frame
        long last_frame = System.nanoTime();
        //Frame courante
        long now = System.nanoTime();
        int frames = 0;
        long last_check = System.currentTimeMillis();
        //Game loop
        while (true){
            //Boucle infinie
            //Update
            now = System.nanoTime();
            if(now - last_frame >= time_per_frame){
                //Refresh = effacer et repaint a chaque frame
                gamePanel.repaint();
                last_frame = now;
                frames++;
            }

            //Compter les frames

            if(System.currentTimeMillis() - last_check >= 1000){
                last_check = System.currentTimeMillis();
                //System.out.println("FPS :" + frames);
                frames = 0;
            }


        }
    }
}
