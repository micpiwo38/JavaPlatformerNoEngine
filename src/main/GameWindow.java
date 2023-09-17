package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel){
        //Instance de la classe jFrame
        jFrame = new JFrame();
        //Fermer le processus
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Ajout du panel
        jFrame.add(gamePanel);

        //Center la fenetre au millieu de l'ecran
        jFrame.setLocationRelativeTo(null);
        //Ne pas redimensioner la fenetre
        jFrame.setResizable(false);
        //Recup Dimension et setPreferdSize du Jpanel + bord et haut de fenetre
        jFrame.pack();
        //Par defaut visible = false
        jFrame.setVisible(true);
    }
}
