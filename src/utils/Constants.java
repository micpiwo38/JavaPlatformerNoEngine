package utils;

import main.Game;

public class Constants {

    //UI
    public static class UI{
        public static class Buttons{
            //Soit Atals 420 * 168
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }
    }

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        //Constante = chaque etat de l'animation du player
        //Determine la colone de Atlas Sprites
        /*
            - 0 = ligne Idle
            - 1 = ligne Run
            - 2 = ligne Jump
            - 3 = ligne Fall
            - 4 = ligne ...
         */
        public static final int IDLE = 0;
        public static final int RUNNING = 1;


        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK = 6;
        public static final int ATTACK_JUMP = 7;

        //On utilise CamelCase pour specifier que la methode est static
        //Cette methode recup le nombre de sprite par animation
        public static int GetSpriteAmount(int player_action){
            return switch (player_action) {
                case RUNNING -> 5;
                case IDLE -> 2;
                case JUMP, FALLING, GROUND, HIT -> 1;
                case ATTACK -> 3;
                case ATTACK_JUMP -> 3;
                default -> 1;
            };
        }
    }
}
