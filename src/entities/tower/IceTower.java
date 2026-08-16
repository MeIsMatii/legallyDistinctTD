package entities.tower;

import entities.projectiles.Ice;
import greenfoot.World;
/**
 * @author Jannis
 */
public class IceTower extends Tower {
    public double slow = 0.5;
    public int slowTimer = 40;

    private double destroyAfter = 20;

    public IceTower() {
        super(200, true, 200, 80, 0, 10, 1, 0);

        upgradeDescription3 = new String[]{"Freeze damages enemy's", "Freeze damages enemy's even more", "The deadlyist freeze"};
        upgradeDescription2 = new String[]{"Ice slows enemy's more", "Ice freezes enemy's completely", "Enemy's are frozen longer"};
        upgradeDescription1 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade3Prices = new int[]{50, 500, 5000};
        upgrade2Prices = new int[]{100, 450, 6000};
        upgrade1Prices = new int[]{75, 350, 3500};

        this.projectileSpawnOffset[0] = -44;
        this.projectileSpawnOffset[1] = -25;
    }

    public double getSlow() {
        return slow;
    }

    public void setSlow(double slow) {
        this.slow = slow;
    }

    public int getSlowTimer() {
        return slowTimer;
    }

    public void setSlowTimer(int slowTimer) {
        this.slowTimer = slowTimer;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        projectileToShoot = new Ice(this);
    }

    public double getDestroyAfter() {
        return destroyAfter;
    }



    @Override
    public String getName() {
        return "IceTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 0;
    }


    public void upgrade(int path) {
        //TODO better upgrades
        switch (path) {
            case 1:
                Ice ice = (Ice) getProjectileToShoot();
                switch (getUpgrade1()) {
                    case 1:
                        destroyAfter =  destroyAfter * 1.1;
                        setRange((getRange() * 1.1));
                        break;
                    case 2:
                        destroyAfter =  destroyAfter * 1.3;
                        setRange((getRange() * 1.3));
                        break;
                    case 3:
                        destroyAfter =  destroyAfter * 1.6;
                        setRange((getRange() * 1.6));
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setSlow(0.3);
                        break;
                    case 2:
                        setSlow(0);
                        break;
                    case 3:
                        setSlowTimer(getSlowTimer() + 5);
                        break;
                }
                break;

            case 3:
                switch (getUpgrade3()) {
                    case 1:
                        setProjectileDamage(1);
                        break;
                    case 2:
                        setProjectileDamage(getProjectileDamage() * 2.0);
                        break;
                    case 3:
                        setProjectileSpeed(getProjectileSpeed() * 3.0);
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
}