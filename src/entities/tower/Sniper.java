package entities.tower;

import entities.enemy.Enemy;

import java.util.List;

public class Sniper extends Tower{
    private int[] upgrades1 = new int[]{150,500,2500,7500,17000};
    private int[] upgrades2 = new int[]{200,450,3000,10000,25000};
    private int[] upgrades3 = new int[]{100,350,1750,6000, 9500};

    public Sniper() {
        super(200, true, 150, 100, 10, 0, 0, 0);
        //GreenfootImage img = new GreenfootImage("tower/sniperTower.png");
        //img.scale(200, 200);
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

    @Override
    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        return "";
    }

    @Override
    public String upgrade2() {
        setUpgrade1(getUpgrade2()+1);
        return "";
    }

    @Override
    public String upgrade3() {
        setUpgrade1(getUpgrade3()+1);
        return "";
    }

    @Override
    void shoot(Enemy e) {
        e.damage(getProjectileDamage());
    }

    @Override
    public String getTowerName() {
        return "Sniper";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }


    public void act() {
        super.act();
        if(canShoot() && !isPlacing()) {
            setShootingDelayCounter(0);
            List<Enemy> enemies = getWorld().getObjects(Enemy.class);
            if(!enemies.isEmpty()) {
                turnTowards(enemies.get(0).getX(),enemies.get(0).getY());
                shoot(enemies.get(0));
            }
        }
    }


}
