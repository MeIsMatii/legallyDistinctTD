package entities.tower;

import entities.enemy.Enemy;

import java.util.List;

public class Sniper extends Tower{
    private final int[] upgrades1 = new int[]{150,500,2500,7500,17000};
    private final int[] upgrades2 = new int[]{200,450,3000,10000,25000};
    private final int[] upgrades3 = new int[]{100,350,1750,6000, 9500};

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
    void shoot(Enemy e) {
        e.damage(getProjectileDamage());
    }

    @Override
    public String getName() {
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
