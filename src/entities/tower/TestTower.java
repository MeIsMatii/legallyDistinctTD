package entities.tower;

import entities.projectiles.TestProjectile;
import greenfoot.World;

public class TestTower extends Tower {


    public TestTower() {
        super(50, true, 300, 45, 1, 3, 1, 45);

        upgradeDescription3 = new String[]{"slightly larger\n range", "even larger\n range", "very large\n range"};
        upgradeDescription2 = new String[]{"slightly more\n piercing", "even more\n piercing", "very much piercing"};
        upgradeDescription1 = new String[]{"slightly faster\n arrows", "even faster\n arrows", "very quick arrows"};


        upgrade1Prices = new int[]{150, 500, 2500};
        upgrade2Prices = new int[]{200, 450, 3000};
        upgrade3Prices = new int[]{100, 350, 1750};
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        projectileToShoot = new TestProjectile(this);
    }

    @Override
    public String getName() {
        return "TestTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }


    public void upgrade(int path) {
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


}
