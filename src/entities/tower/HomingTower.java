package entities.tower;

import entities.projectiles.HomingProjectile;
import greenfoot.World;

public class HomingTower extends Tower {


    public HomingTower() {
        super(200, true, 300, 45, 7, 1, 2, 10);

        upgradeDescription1 = new String[]{"Greater damage", "even greater damage", "One shot to\nrule them all"};
        upgradeDescription2 = new String[]{"Faster energy", "even faster energy", "the flash"};
        upgradeDescription3 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade3Prices = new int[]{50, 500, 5000};
        upgrade2Prices = new int[]{100, 450, 6000};
        upgrade1Prices = new int[]{75, 350, 3500};

        this.projectileSpawnOffset[0] = 0;
        this.projectileSpawnOffset[1] = -35;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        projectileToShoot = new HomingProjectile(this);
    }


    public void act() {
        if (isPaused()) {
            return;
        }
        super.act();
    }


    @Override
    public String getName() {
        return "HomingTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 3;
    }

    public void upgrade(int path) {
        //TODO better upgrades
        switch (path) {
            case 1:
                setProjectileDamage(getProjectileDamage()*2);
                setProjectileSpeed(getProjectileSpeed()/1.2);
                setShootingDelay(getShootingDelay()*2);
                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setProjectilePiercing(getProjectilePiercing() * 1.5);
                        break;
                    case 2:
                        setProjectilePiercing(getProjectilePiercing() * 2);
                        break;
                    case 3:
                        setProjectilePiercing(getProjectilePiercing() * 3);
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
