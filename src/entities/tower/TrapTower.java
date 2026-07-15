package entities.tower;

import entities.Entity;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.projectiles.Explosion;
import maps.levels.util.Path;
import util.Clickable;

import java.util.List;

public class TrapTower extends Tower implements Clickable {
    private final int mineRadius;

    private final int[] upgrades1 = new int[]{150, 500, 2500, 7500, 17000};
    private final int[] upgrades2 = new int[]{200, 450, 3000, 10000, 25000};
    private final int[] upgrades3 = new int[]{100, 350, 1750, 6000, 9500};

    public TrapTower() {
        super(125, true, 80, 99999999, 200, 10, 1, 90);
        mineRadius = 150;

        //setImage("comingSoon.png");
    }

    public int[] getUpgrades1() {
        return upgrades1;
    }

    public int[] getUpgrades2() {
        return upgrades2;
    }

    public int[] getUpgrades3() {
        return upgrades3;
    }


    public void shoot(Enemy e) {
        e.damage(getProjectileDamage());
        //setImage("Explosion.png");
        //getImage().scale(100, 100);
        //Greenfoot.delay(20);
        getWorld().addObject(new Explosion(), getX(), getY());
        getWorld().removeObject(this);
    }

    @Override
    public String getName() {
        return "TrapTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }

    public void act() {
        if (isPaused()) return;
        super.act();
    }

    public void onHit(Entity hitter) {
        //TODO explode when touching enemy (mine projectile is not necessary) @sinnaJ @Jannis

    }


    public void checkPlacement() {
        List<Hitbox> hitboxes = getIntersectingObjects(Hitbox.class);
        setCanPlace(false);
        for (Hitbox hitbox : hitboxes) {
            if (hitbox.getOwner() instanceof Path) {
                setCanPlace(true);
                break;
            }
        }

        if (canPlace()) {
            getRangeDisplay().setRangeVisibility(true, getColorGrey());
        } else {
            getRangeDisplay().setRangeVisibility(true, getColorRed());
        }
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
