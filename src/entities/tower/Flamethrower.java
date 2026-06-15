package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.HomingProjectile;
import entities.projectiles.TestProjectile;

public class Flamethrower extends Tower{
    private int magazine = 15;
    private int rechargeCounter;

    public Flamethrower(int upgrade1, int upgrade2, int upgrade3) {
        super(150,true, 100, 1, 1, 10, 1000, 45);
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
            getWorld().addObject(new TestProjectile(getProjectileSpeed(), getProjectilePiercing(), getProjectileDamage(), e.getX(), e.getY(), getProjectileIFrames()), getX(), getY());
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

    @Override
    public void act() {
        super.act();
    }
}
