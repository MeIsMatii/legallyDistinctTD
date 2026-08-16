package entities.tower;

import entities.projectiles.Rocket;
import greenfoot.World;

public class Rocketlauncher extends Tower {


    public Rocketlauncher() {
        super(500, true, 500, 150, 20, 10, 1, 0);

        //TODO upgrades
        upgradeDescription1 = new String[]{"More range", "Even more range", "Very long range"};
        upgradeDescription3 = new String[]{"Faster projectiles", "Even faster projectiles", "The fastest projectiles"};
        upgradeDescription2 = new String[]{"Piercing", "Even more piercing", "Very long piercing"};

        upgrade3Prices = new int[]{50, 500, 5000};
        upgrade2Prices = new int[]{100, 450, 6000};
        upgrade1Prices = new int[]{75, 350, 3500};

        this.projectileSpawnOffset[0] = 0;
        this.projectileSpawnOffset[1] = 10;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        projectileToShoot = new Rocket(this);
    }


    @Override
    public String getName() {
        return "Rocketlauncher";
    }

    @Override
    public int getAnimationSpeed() {
        return 10;
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