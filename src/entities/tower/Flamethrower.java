package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.HomingProjectile;
import entities.projectiles.TestProjectile;
import greenfoot.GreenfootImage;

public class Flamethrower extends Tower{
    private int magazine = 15;
    private int rechargeCounter;

    public Flamethrower() {
        super(150,true, 300, 1, 1, 10, 1000, 45);
        GreenfootImage img = new GreenfootImage("oldHomeButton.png");
        img.scale(200,200);
        setImage(img);
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
            getWorld().addObject(new TestProjectile(this),getX(),getY());
            magazine--;
        }else {
            recharge();
        }
    }
    private void recharge() {
        int rechargeDelay = 50;
        if (rechargeCounter < rechargeDelay) {
            rechargeCounter++;
            return;
        }
        magazine = magazine + 15;
    }

    public void act() {
        if(isPaused()) return;
        super.act();
    }
}
