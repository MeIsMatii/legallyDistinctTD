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
    private int upgrade1 = 1;
    private int upgrade2 = 1;
    private int upgrade3 = 1;
    //Upgrades need: str description [done] + int Level + option to buy

    public void shoot(Enemy e) {
        getWorld().addObject(new TestProjectile(getProjectileSpeed(), getProjectileDamage(), getProjectilePiercing(), e.getX(),e.getY(), getProjectileIFrames()), getX(), getY());
    }
    public int getUpgrade1() {
        return upgrade1;
    }

    public int getUpgrade2() {
        return upgrade2;
    }

    public int getUpgrade3() {
        return upgrade3;
    }

    public void setUpgrade1(int upgrade1) {
        this.upgrade1 = upgrade1;
    }

    public void setUpgrade2(int upgrade2) {
        this.upgrade2 = upgrade2;
    }

    public void setUpgrade3(int upgrade3) {
        this.upgrade3 = upgrade3;
    }

    public String upgrade1(){
        upgrade1++;
    System.out.println("test upgrade 1" +" "+ upgrade1);
    return "test1";
    }
    public String upgrade2(){
        upgrade2++;
        System.out.println("test upgrade 2" + " "+ upgrade2);
        return "test2";
    }
    public String  upgrade3(){
        upgrade3++;
        System.out.println("test upgrade 3" +" "+ upgrade3);
        return "test3";
    }
    public void act() {
        if(isPaused()) {
            return;
        }
        super.act();
    }
}
