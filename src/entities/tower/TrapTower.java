package entities.tower;

import entities.Entity;
import entities.Hitbox;
import entities.base.Enemy;
import entities.base.Tower;
import entities.projectiles.Explosion;
import greenfoot.World;
import maps.levels.util.Path;
import util.Clickable;

import java.util.List;
/**
 * @author Jannis
 */

public class TrapTower extends Tower implements Clickable {


    public TrapTower() {
        super(125, true, 80, 99999999, 40, 10, 1, 90);


        upgradeDescription1 = new String[]{"no upgrades available", "no upgrades available", "no upgrades available"};
        upgradeDescription2 = new String[]{"no upgrades available", "no upgrades available", "no upgrades available"};
        upgradeDescription3 = new String[]{"no upgrades available", "no upgrades available", "no upgrades available"};

        upgrade3Prices = new int[]{0, 0, 0};
        upgrade2Prices = new int[]{0, 0, 0};
        upgrade1Prices = new int[]{0, 0, 0};

        this.projectileSpawnOffset[0] = 0;
        this.projectileSpawnOffset[1] = 0;
    }

    public void onClick() {
        if(!isPlacing()) {
            return;
        }
        super.onClick();
    }



    public void shoot(Enemy e) {
        if(getIntersectingObjects(Enemy.class).isEmpty()){
            return;
        }


        super.shoot(e);
        getWorld().removeObject(this);
    }

    @Override
    public void addedToWorld(World world) {
        super.addedToWorld(world);
        projectileToShoot = new Explosion(this);
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
                        break;
                    case 3:
                        setRange((getRange() * 1.5));
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setProjectileDamage(getProjectileDamage()+5);

                        break;
                    case 2:
                        setProjectileDamage(getProjectileDamage()+10);
                        break;
                    case 3:
                        setProjectileDamage(getProjectileDamage()+15);
                        break;
                }
                break;

            case 3:
                Explosion explosion = (Explosion) getProjectileToShoot();
                explosion.setExplosionCounter(explosion.getExplosionCounter()+10);
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }

}
