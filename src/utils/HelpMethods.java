package utils;

import main.Game;

import java.awt.geom.Rectangle2D;

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
        //Si la position du player < 0 ou depasse la largeur de l'ecran
        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        //Idem pour la hauteur de l'ecran
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        //RAPPEL => TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        //32 * 2f
        //Ce qui est solide est donc la position du player (x et y) / 64
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        //On commence par le verticale => horizontale
        int value = lvlData[(int) yIndex][(int) xIndex];
        //Value represente un tableau 2D de tous les elements solides
        //Ici 11 est un sprite vide dans mon level Atlas
        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;
        //Si cette methode retoune TRUE c'est que le player est en collision avec une tuile du niveau
    }

    //Check les limites du niveau GAUCHE ET DROITE et la hitbox du player
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        //Tuile courante = hitbox du player X / TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        int currentTile = (int) hitbox.x / Game.TILES_SIZE;
        //Si la vitesse est > 0 (donc le player ce deplace)
        if(xSpeed > 0){
            //RIGHT
            //Taille de la tuile et taille du player
            int tileXPos = currentTile * Game.TILES_SIZE;
            //Offset entre la taille de la tuile et la position du player
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            // -1 pour etre en dehors de la tuile suivante ou précedente
            return tileXPos + xOffset - 1;
        }else{
            //LEFT
            return currentTile * Game.TILES_SIZE;
        }
    }

    //Check les limites entre la hotbox du player et le plafond - sol du niveau
    public static float GetEntityYPosUnderRoofAboveFloor(Rectangle2D.Float hitbox, float airSpeed){
        //Tuile courante = hitbox du player X / TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        int currentTile = (int) hitbox.y / Game.TILES_SIZE;
        if(airSpeed > 0){
            //DOWN FALLING = touche le sol
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        }else{
            //UP JUMPING = touche le plafond
            return currentTile * Game.TILES_SIZE;
        }
    }

    //Check si le player ne touche + le sol = applique la gravité
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox,int[][] lvlData){
        //Check les pixels avec les angles de la hitbox Player (bottom_left && bottom_right)
        //bottom_left = hitbox.y + hitbox.height
        //bottom_right = hitbox.x + hitbox.width && hitbox.y + hitbox.height
        //Si les angles bottom gauche et droite ne sont pas en collision avec une tuile du niveau
        //+ 1 pour la tuile suivante
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
                return false;
        //Sinon on touche le sol
        return true;
    }
}
