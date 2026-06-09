package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.TestProjectile;
import greenfoot.GreenfootImage;

public class TestTower extends Tower {
    public TestTower(boolean isPlacing, int upgrade1lvl, int upgrade2lvl, int upgrade3lvl) {
        super(isPlacing, 300, 45, 1, 3, 10, 45);
        setImage("TestTower2_2.png");

        GreenfootImage img = getImage();
        //img.scale(100, 100);
        setImage(img);

    }
    //Upgrades need: str description [done] + int Level + option to buy

    public void shoot(Enemy e) {
        getWorld().addObject(new TestProjectile(getProjectileSpeed(), getProjectileDamage(), getProjectilePiercing(), e.getX(), e.getY(), getProjectileIFrames()), getX(), getY());
    }


    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        System.out.println("test upgrade 1" + " " + getUpgrade1());
        return "test1";
    }

    public String upgrade2() {
        setUpgrade2(getUpgrade2()+1);
        System.out.println("test upgrade 2" + " " + getUpgrade2());
        return "test2";
    }

    public String upgrade3() {
        setUpgrade3(getUpgrade3()+1);
        System.out.println("test upgrade 3" + " " + getUpgrade3());
        return "test3";
    }

    public void act() {
        if (isPaused()) {
            return;
        }
        super.act();
    }
}
