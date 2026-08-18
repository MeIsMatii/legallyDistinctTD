package entities.tower;

import entities.base.Enemy;
import entities.base.Tower;
import entities.projectiles.FlameProjectile;
import greenfoot.World;

// Author: @Elias

public class Flamethrower extends Tower {

    private int magazine = 4;
    private final int maxMagazine = 4;
    private int rechargeCounter;
    private int rechargeDelay = 400; // Promoted to field so upgrades can alter it

    public Flamethrower() {
        super(350, true, 300, 1, 1, 10, 1, 45);
        this.projectileSpawnOffset[0] = 0;
        this.projectileSpawnOffset[1] = -10;
    }

    @Override
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        projectileToShoot = new FlameProjectile(this);

        upgradeDescription3 = new String[]{"Faster reload", "Even faster reload", "The fastest Flamethrower"};
        upgradeDescription2 = new String[]{"Yellow hot", "White hot", "Blue hot"};
        upgradeDescription1 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade3Prices = new int[]{50, 3500, 12000};
        upgrade2Prices = new int[]{100, 3050, 14000};
        upgrade1Prices = new int[]{75, 1250, 10500};
    }

    @Override
    public void shoot(Enemy e) {
        if (magazine <= 0) {
            return; // Prevent shooting while empty; recharge() now runs in act()
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

    private void recharge() {
        if (magazine >= maxMagazine) return; // Full ammo, no recharge needed

        if (rechargeCounter < rechargeDelay) {
            rechargeCounter++;
        } else {
            magazine = maxMagazine;
            rechargeCounter = 0;
        }
    }

    @Override
    public void act() {
        if (isPaused()) return;
        recharge(); // Continuously processes reload timer every tick
        super.act();
    }

    @Override
    public void upgrade(int path) {
        switch (path) {
            case 1:
                switch (getUpgrade1()) {
                    case 1:
                        setRange((int) (getRange() * 1.1));
                        break;
                    case 2:
                        setRange((int) (getRange() * 1.3));
                        break;
                    case 3:
                        setRange((int) (getRange() * 1.5));
                        break;
                }
                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setProjectilePiercing((int) (getProjectilePiercing() * 1.5));
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
                // Decreases downtime between magazines without breaking shot pacing
                switch (getUpgrade3()) {
                    case 1:
                        rechargeDelay = 250;
                        break;
                    case 2:
                        rechargeDelay = 200;
                        break;
                    case 3:
                        rechargeDelay = 160;
                        break;
                }
                break;

            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
}