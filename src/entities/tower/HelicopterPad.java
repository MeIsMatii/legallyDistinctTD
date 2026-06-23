package entities.tower;

import entities.Entity;
import entities.enemy.Enemy;
import greenfoot.World;

public class HelicopterPad extends Tower {
    public HelicopterPad() {
        super(0, true, 150, 99999999, 0, 0, 0, 0);
    }
    @Override
    public void onHit(Entity hitter) {
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        if(!getPlacing()) {
            Helicopter heli = new Helicopter();
            heli.setPlacing(false);
            getWorld().addObject(heli,getX(),getY());
        }
    }

    public void place() {
        super.place();
        Helicopter heli = new Helicopter();
        heli.setPlacing(false);
        getWorld().addObject(heli,getX(),getY());
    }

    @Override
    public String upgrade1() {
        return "";
    }

    @Override
    public String upgrade2() {
        return "";
    }

    @Override
    public String upgrade3() {
        return "";
    }

    @Override
    void shoot(Enemy e) {

    }

    @Override
    public String getTowerName() {
        return "HelicopterPad";
    }

    @Override
    public int getAnimationSpeed() {
        return 0;
    }
}
