package entities;

//Une classe abstraite ne peu pas etre instanciée mais les autres classe peuvent en héritée via extends
public abstract class Entity {

    protected float x, y;
    protected int width, height;
    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}
