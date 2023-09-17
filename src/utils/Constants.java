package utils;

public class Constants {

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        //Constante = chaque etat de l'animation du player
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
