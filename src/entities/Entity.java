package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

//Une classe abstraite ne peu pas etre instanciée mais les autres classe peuvent en héritée via extends
public abstract class Entity {

    //Position d'un element de l'entité Horizontale et verticale
    protected float x, y;
    //Largeur et hauteur
    protected int width, height;

    //La classe Rectangle possède de nombreuses methodes pour les données d'un objet
    protected Rectangle2D.Float hitbox;
    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //Color la hitbox pour le debug
    protected void drawHitbox(Graphics graphics){
        graphics.setColor(Color.PINK);
        graphics.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void initHitbox(float x, float y, int width, int height) {
        //Instance de la classe Rectangle
        //x , y, w, h
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }


}
