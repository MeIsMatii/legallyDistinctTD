package entities.tower;

import entities.base.Tower;
import entities.projectiles.HomingProjectile;
import greenfoot.World;

public class HomingTower extends Tower {


    public HomingTower() {
        super(250, true, 250, 80, 5, 1, 1, 10);

        upgradeDescription1 = new String[]{"Greater damage", "even greater damage", "One shot to\nrule them all"};
        upgradeDescription2 = new String[]{"Faster energy", "even faster energy", "the flash"};
        upgradeDescription3 = new String[]{"More piercing", "Even more piercing", "Max piercing"};

        upgrade3Prices = new int[]{75, 1500, 7000};
        upgrade2Prices = new int[]{150, 1450, 6500};
        upgrade1Prices = new int[]{100, 1350, 4000};

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
                        setProjectilePiercing(getProjectilePiercing() * 1.5);
                        break;
                    case 2:
                        setProjectilePiercing(getProjectilePiercing() * 1.7);
                        break;
                    case 3:
                        setProjectilePiercing(getProjectilePiercing() * 2);
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
}
