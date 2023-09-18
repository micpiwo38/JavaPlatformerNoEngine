package utils;

import main.Game;

public class HelpMethods {
    //Classe generique qui retourne des valeurs

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData){
        //les 4 angles du player
        //TOP LEFT
        if(!IsSolid(x,y,lvlData))
            //BOTTOM RIGHT
            if(!IsSolid(x+width, y+height, lvlData))
                //TOP RIGHT
                if(!IsSolid(x+width,y,lvlData))
                    //BOTTOM LEFT
                    if(!IsSolid(x, y+height,lvlData))
                        return true;
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData){
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        //RAPPEL => TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        //32 * 2f
        //Ce qui est solide est donc la position du player (x et y) / 64
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        //On commence par le verticale => horizontale
        int value = lvlData[(int) yIndex][(int) xIndex];
        //Value represente un tableau 2D de tous les elements solide
        //Ici 11 est un sprite vide dans mon level Atlas
        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;

    }
}
