package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.Explosion;
import entities.projectiles.Ice;
import entities.projectiles.Rocket;
import entities.projectiles.TestProjectile;
import greenfoot.GreenfootImage;

public class IceTower extends Tower{
    private final int[] upgrades1 = new int[]{150,500,2500};
    private final int[] upgrades2 = new int[]{200,450,3000};
    private final int[] upgrades3 = new int[]{100,350,1750};

    private final String[] upgradeDescription3 = new String[]{"Freeze damages enemy's", "Freeze damages enemy's even more","The deadlyist freeze","final upgrade done"};
    private final String[] upgradeDescription2 = new String[]{"Ice slows enemy's more", "Ice freezes enemy's completely", "Enemy's are frozen longer", "final upgrade done"};
    private final String[] upgradeDescription1 = new String[]{"More range", "Even more range","Very long range","final upgrade done"};



    public double getSlow() {
        return slow;
    }

    public void setSlow(double slow) {
        this.slow = slow;
    }

    public double slow = 0.5;

    public int getSlowTimer() {
        return slowTimer;
    }

    public void setSlowTimer(int slowTimer) {
        this.slowTimer = slowTimer;
    }

    public int slowTimer = 50;


    public IceTower() {
        super(200, true, 200, 80, 0, 25, 1, 0);
        //GreenfootImage img = new GreenfootImage("Credits.jpg");
        //img.scale(200,200);
        //setImage(img);
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


    public String getUpgradeDescription1() {
        return upgradeDescription1[getUpgrade1()];
    }

    public String getUpgradeDescription2() {
        return upgradeDescription2[getUpgrade2()];
    }

    public String getUpgradeDescription3() {
        return upgradeDescription3[getUpgrade3()];
    }


    @Override
    public void shoot(Enemy e) {
        getWorld().addObject(new Ice(this), getX(), getY());
    }

    @Override
    public String getName() {
        return "IceTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
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
                        break;
                    case 3:
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
                        setSlowTimer(getSlowTimer()+40);
                        break;
                }
                break;

            case 3:
                switch (getUpgrade3()) {
                    case 1:
                        setProjectileDamage(1);
                        break;
                    case 2:
                        setProjectileDamage(getProjectileDamage() * 1.1);
                        break;
                    case 3:
                        setProjectileSpeed(getProjectileSpeed() * 1.3);
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
}