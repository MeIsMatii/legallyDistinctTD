package entities.enemy;

import entities.Entity;
import entities.Hitbox;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class EnemyHitbox extends Hitbox {

    public EnemyHitbox(int width, int height, Entity owner) {
        super(width, height, owner);
    }

    public GreenfootImage drawHitbox(int w, int h, Color color, boolean fill) {
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(color);
        if(fill) {
            img.fillOval(0, 0, w - 1, h - 1);
            img.setColor(Color.BLACK);
        }
        img.drawOval(0, 0, w - 1, h - 1);




        return img;
    }
}
