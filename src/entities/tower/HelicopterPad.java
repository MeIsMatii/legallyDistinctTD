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
        if(!isPlacing()) {
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
    void shoot(Enemy e) {

    }

    @Override
    public String getName() {
        return "HelicopterPad";
    }

    @Override
    public int getAnimationSpeed() {
        return 0;
    }

    public void upgrade(int path) {
        //TODO better upgrades
        onUpgrade(path);
        switch (path) {
            case 1:
                switch (getUpgrade1()) {
                    case 1:
                        setRange((getRange() * 1.1));
                        break;
                    case 2:
                        setRange((getRange() * 1.3));
                        //some kinda different behaviour
                        break;
                    case 3:
                        setRange((getRange() * 1.5));
                        //some kinda different behaviour
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setProjectilePiercing(getProjectilePiercing() * 1.5);

                        break;
                    case 2:
                        setProjectilePiercing(getProjectilePiercing() * 2);
                        //some kinda different behaviour
                        break;
                    case 3:
                        setProjectilePiercing(getProjectilePiercing() * 3);
                        //some kinda different behaviour
                        break;
                }
                break;

            case 3:
                switch (getUpgrade3()) {
                    case 1:
                        setProjectileSpeed(getProjectileSpeed() * 1.5);
                        break;
                    case 2:
                        setProjectileSpeed(getProjectileSpeed() * 2);
                        //some kinda different behaviour
                        break;
                    case 3:
                        setProjectileSpeed(getProjectileSpeed() * 3);
                        //some kinda different behaviour
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
}
