package levels;

public class Level {

    private int[][] lvlData;

    public Level(int[][] lvlData){
        this.lvlData = lvlData;
    }

    public int getSpriteIndex(int x, int y){
        return lvlData[y][x];
    }

    //Retourner le niveau courant
    public int[][] getLevelData(){
        return lvlData;
    }
}
