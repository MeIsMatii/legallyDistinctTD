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

    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        System.out.println("flamethrower upgrade 1" + " " + getUpgrade1());
        return "flamethrower 1";
    }

    public String upgrade2() {
        setUpgrade2(getUpgrade2()+1);
        System.out.println("flamethrower upgrade 2" + " " + getUpgrade2());
        return "flamethrower 2";
    }

    public String upgrade3() {
        setUpgrade3(getUpgrade3()+1);
        System.out.println("flamethrower upgrade 3" + " " + getUpgrade3());
        return "flamethrower 3";
    }

    @Override
    void shoot(Enemy e) {
        if (magazine > 0){
            getWorld().addObject(new FlameProjectile(this),getX(),getY());
            magazine--;
        }else {
            recharge();
        }
    }

    @Override
    public String getTowerName() {
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
}
