package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.TestProjectile;
import greenfoot.World;
import util.Animations;

import java.util.List;

public class TestTower extends Tower{


    public TestTower() {
        super(50, true, 300, 45, 1, 3, 1, 45);
    }
    //Upgrades need: str description [done] + int Level + option to buy

    public void shoot(Enemy e) {
        getWorld().addObject(new TestProjectile(this), getX(), getY());
    }

    @Override
    public String getTowerName() {
        return "TestTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }



    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        System.out.println("test upgrade 1" + " " + getUpgrade1());
        upgrade(1);
        return "test1";

    }

    public String upgrade2() {
        setUpgrade2(getUpgrade2()+1);
        System.out.println("test upgrade 2" + " " + getUpgrade2());
        upgrade(2);
        return "test2";
    }

    public String upgrade3() {
        setUpgrade3(getUpgrade3()+1);
        System.out.println("test upgrade 3" + " " + getUpgrade3());
        upgrade(3);
        return "test3";
    }

    public void upgrade(int path) {
        onUpgrade(path);
        switch(path) {
            case 1:
                switch (getUpgrade1()) {
                    case 1:
                        setProjectileDamage((getProjectileDamage() * 1.5));
                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;

            case 3:
                switch (getUpgrade3()) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }

    public void act() {
        if (isPaused()) {
            return;
        }


        super.act();
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
    }


}
