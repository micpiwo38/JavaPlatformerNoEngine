package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    //Cette classe retourne les images
    //Planche player atlas
    public static final String PLAYER_ATLAS = "player_sprites.png";
    //Planche level Atals
    public static final String LEVEL_ATLAS = "levels.png";
    //Image du level 1
    public static final String LEVEL_ONE_DATA = "level_one_data.png";

    public static BufferedImage GetSpriteAtlas(String filename){
        BufferedImage img = null;
        //La planche possède des cases de 64 * 40
        //Charger la planche de sprite player 32 * 32 px au centre des cases
        InputStream is = LoadSave.class.getResourceAsStream("../main/res/" + filename);
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
        return img;
    }

    //Cette methode cree un tableau multi dimmension 26 * 14
    //On recup une image de 26 * 14
    //Les couleurs RGB vont etre remplacée par les tuiles
    public static int[][] GetLevelData(){
        //tableau multi dimmension 26 * 14
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        //Appel de l'image 26 * 14
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        //Boucle des colonnes
        for(int j = 0; j < img.getHeight(); j++)
            for(int i = 0; i < img.getWidth(); i++){
                //Recup des couleurs RGB
                Color color = new Color(img.getRGB(i, j));
                //Eviter les out of bounds
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                //Recup le rouge de la matrice
                lvlData[j][i] = value;
            }
        return lvlData;
    }
}
