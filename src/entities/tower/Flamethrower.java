package entities.tower;

import entities.base.Enemy;
import entities.base.Tower;
import entities.projectiles.FlameProjectile;
import greenfoot.World;

// Author: @Elias

public class Flamethrower extends Tower {


    private int magazine = 4;
    private int rechargeCounter;

    public Flamethrower() {
        super(350, true, 300, 1, 1, 10, 1, 45);
        this.projectileSpawnOffset[0] = 0;
        this.projectileSpawnOffset[1] = -10;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        projectileToShoot = new FlameProjectile(this);

        upgradeDescription3 = new String[]{"Faster shooting", "Even faster shooting", "The fastest Flamethrower"};
        upgradeDescription2 = new String[]{"Yellow hot", "White hot", "Blue hot"};
        upgradeDescription1 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade3Prices = new int[]{50, 1500, 6000};
        upgrade2Prices = new int[]{100, 1050, 7000};
        upgrade1Prices = new int[]{75, 650, 5500};
    }


    @Override
    public void shoot(Enemy e) {
        if(magazine <= 0) {
            recharge();
            return;
        }

        magazine--;
        playSound("fire.mp3");
        super.shoot(e);
    }

    @Override
    public String getName() {
        return "Flamethrower";
    }

    @Override
    public int getAnimationSpeed() {
        return 0;
    }

    private void recharge() { //works, no need to touch that ever again
        int rechargeDelay = 400;
        if (rechargeCounter < rechargeDelay) {
            rechargeCounter++;
            return;
        }
        magazine = magazine + 4;
        rechargeCounter = 0;
    }

    public void act() {
        if (isPaused()) return;
        super.act();
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
