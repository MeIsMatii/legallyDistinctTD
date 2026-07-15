package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.TestProjectile;
import greenfoot.World;

public class TestTower extends Tower {


    public TestTower() {
        super(50, true, 300, 45, 1, 3, 1, 45);
    }
    //Upgrades need: str description [done] + int Level + option to buy

    public void shoot(Enemy e) {
        getWorld().addObject(new TestProjectile(this), getX(), getY());
    }

    @Override
    public String getName() {
        return "TestTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }

    public void defineDescriptions() {
        setUpgradeDescription1(new String[]{"slightly larger\n range", "even larger\n range","very large\n range"});
        setUpgradeDescription2(new String[]{"slightly more\n piercing", "even more\n piercing","very much piercing"});
        setUpgradeDescription3(new String[]{"slightly faster\n arrows", "even faster\n arrows","very quick arrows"});
    }


    public String upgrade1() {
        setUpgrade1(getUpgrade1() + 1);
        System.out.println("test upgrade 1" + " " + getUpgrade1());
        upgrade(1);
        return "test1";

    }

    public String upgrade2() {
        setUpgrade2(getUpgrade2() + 1);
        System.out.println("test upgrade 2" + " " + getUpgrade2());
        upgrade(2);
        return "test2";
    }

    public String upgrade3() {
        setUpgrade3(getUpgrade3() + 1);
        System.out.println("test upgrade 3" + " " + getUpgrade3());
        upgrade(3);
        return "test3";
    }


    public void upgrade(int path) {
        onUpgrade(path);
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
