package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private  Level levelOne;
    public LevelManager(Game game){
        this.game = game;
        //levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprite();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprite() {
        //Appel de l'Atlas = 384 * 128
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        //12 en horizontale et 4 en verticale soit 12 * 4 = 48
        levelSprite = new BufferedImage[48];
        //Parcours des colonnes verticales
        for(int j = 0; j < 4; j++)
            //Parcours des lignes Horizontale
            for(int i = 0; i < 12; i++){
                //index = colonnes * 12 + lignes
                int index = j * 12 + i;
                //Subimage recupère la decoupe de l'atlas des sprites 32 * 32
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }

    }


    public void draw(Graphics graphics){
        //Tant que colonne est < 26
        for(int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            //Tant que ligne < 14
            for(int i = 0; i < Game.TILES_IN_WIDTH; i++){
                //Index
                int index = levelOne.getSpriteIndex(i, j);
                //On dessine pour chaque index pos X, pos Y , largeur et hauteur des tuiles
                //Pour rappel : TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); soit 32 * 1.5f
                graphics.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }

    }

    public void update(){

    }

    public Level getCurrentLevel(){
        return levelOne;
    }
}
