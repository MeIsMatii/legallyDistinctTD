package entities.tower;

import entities.projectiles.HomingProjectile;
import greenfoot.World;

public class HomingTower extends Tower {


    public HomingTower() {
        super(100, true, 300, 45, 20, 1, 2, 10);

        upgradeDescription1 = new String[]{"Faster shooting", "Even faster shooting", "The fastest Flamethrower"};
        upgradeDescription2 = new String[]{"Yellow hot", "White hot", "Blue hot"};
        upgradeDescription3 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade1Prices = new int[]{150, 500, 2500, 7500, 17000};
        upgrade2Prices = new int[]{200, 450, 3000, 10000, 25000};
        upgrade3Prices = new int[]{100, 350, 1750, 6000, 9500};

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
