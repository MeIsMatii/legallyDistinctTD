package entities.tower;

import entities.Entity;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.projectiles.Explosion;
import entities.projectiles.Ice;
import greenfoot.World;
import maps.levels.util.Path;
import util.Clickable;

import java.util.List;
/**
 * @author Jannis
 */

public class TrapTower extends Tower implements Clickable {
    private final int mineRadius;

    private final int[] upgrades1 = new int[]{150, 500, 2500};
    private final int[] upgrades2 = new int[]{200, 450, 3000};
    private final int[] upgrades3 = new int[]{100, 350, 1750};

    private final String[] upgradeDescription3 = new String[]{"Slightly longer explosion", "Even longer explosion", "The longest explosion ever!"};
    private final String[] upgradeDescription2 = new String[]{"Slightly more damage", "Even more damage", "The deadlyist bomb!"};
    private final String[] upgradeDescription1 = new String[]{"Slightly bigger explosion", "Even more bigger explosion", "Very large explosion"};

    public TrapTower() {
        super(125, true, 80, 99999999, 10, 10, 1, 90);
        mineRadius = 150;
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
