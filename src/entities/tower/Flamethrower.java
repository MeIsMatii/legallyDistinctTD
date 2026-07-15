package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.FlameProjectile;
import entities.projectiles.HomingProjectile;
import entities.projectiles.Rocket;
import entities.projectiles.TestProjectile;
import greenfoot.GreenfootImage;
import ui.hud.UpgradePath;

import java.util.List;

public class Flamethrower extends Tower{
    private int magazine = 15;
    private int rechargeCounter;
    private final int[] upgrades1 = new int[]{150,500,2500};
    private final int[] upgrades2 = new int[]{200,450,3000};
    private final int[] upgrades3 = new int[]{100,350,1750};
    private final String[] upgradeDescription1 = new String[]{"Faster shooting", "Even faster shooting","The fastest Flamethrower","final upgrade done"};
    private final String[] upgradeDescription2 = new String[]{"Yellow hot", "White hot","Blue hot","final upgrade done"};
    private final String[] upgradeDescription3 = new String[]{"More range", "Even more range","Very long range","final upgrade done"};

    public Flamethrower() {
        super(150,true, 300, 1, 1, 10, 1000, 45);
        //GreenfootImage img = new GreenfootImage("towers/Flamethrower/Flamethrower_idle.jpg");
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
    void shoot(Enemy e) {
        if (magazine > 0){
            playSound("fire.mp3");
            getWorld().addObject(new FlameProjectile(this),getX(),getY());
            magazine--;
        }else {
            recharge();
        }
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
        int rechargeDelay = 50;
        if (rechargeCounter < rechargeDelay) {
            rechargeCounter++;
            return;
        }
        magazine = magazine + 15;
        rechargeCounter = 0;
    }

    public void act() {
        if(isPaused()) return;
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
