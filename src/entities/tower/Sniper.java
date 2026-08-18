package entities.tower;

import entities.base.Enemy;
import entities.base.Tower;

import java.util.List;

public class Sniper extends Tower {

    public Sniper() {
        super(200, true, 150, 90, 15, 0, 0, 0);

        upgradeDescription1 = new String[]{"Little Donation", "bigger donation", "biggest donation"};
        upgradeDescription3 = new String[]{"More Damage", "Even more Damage", "The most Damage"};//weg
        upgradeDescription2 = new String[]{"Faster firing  rate", "Even faster firing  rate", "Max firing  rate"};//weg


        upgrade3Prices = new int[]{50, 500, 5000};
        upgrade2Prices = new int[]{100, 450, 6000};
        upgrade1Prices = new int[]{75, 350, 3500};

        //not used bc it does not spawn projectiles
        this.projectileSpawnOffset[0] = 0;
        this.projectileSpawnOffset[1] = 0;
    }


    @Override
    public void shoot(Enemy e) {
        e.damage(getProjectileDamage());
    }

    @Override
    public String getName() {
        return "Sniper";
    }

    @Override
    public int getAnimationSpeed() {
        return 4;
    }


    public void act() {
        super.act();
        if (canShoot() && !isPlacing()) {
            startAnimation();
            setShootingDelayCounter(0);
            List<Enemy> enemies = getWorld().getObjects(Enemy.class);
            if (!enemies.isEmpty()) {
                //turnTowards(enemies.get(0).getX(), enemies.get(0).getY());
                shoot(enemies.get(0));
            }
        }
    }

    public void upgrade(int path) {
        switch (path) {
            case 1:
                switch (getUpgrade1()) {
                    case 1:
                    case 2:
                    case 3:
                        //no third option available
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setShootingDelay(getShootingDelay() - 5);

                        break;
                    case 2:
                        setShootingDelay(getShootingDelay() - 6);
                        break;
                    case 3:
                        setShootingDelay(getShootingDelay() - 7);
                        break;
                }
                break;

            case 3:
                switch (getUpgrade3()) {
                    case 1:
                        setProjectileDamage(getProjectileDamage() * 1.2);
                        break;
                    case 2:
                        setProjectileDamage(getProjectileDamage() * 1.4);
                        break;
                    case 3:
                        setProjectileDamage(getProjectileDamage() * 1.6);
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }


}
