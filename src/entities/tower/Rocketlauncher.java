package entities.tower;

import entities.projectiles.Rocket;
import greenfoot.World;

public class Rocketlauncher extends Tower {


    public Rocketlauncher() {
        super(500, true, 200, 150, 20, 10, 1, 0);

        //TODO upgrades
        upgradeDescription3 = new String[]{"Faster Projectiles", "Even faster Projectiles", "MAX Speed Projectiles"};
        upgradeDescription2 = new String[]{"More Projectile Piercing", "even more Projectile Piercing", "MAX Projectile Piercing"};
        upgradeDescription1 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade3Prices = new int[]{150, 750, 6000};
        upgrade2Prices = new int[]{100, 1050, 8000};
        upgrade1Prices = new int[]{125, 650, 3500};

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
        switch (path) {
            case 1:
                switch (getUpgrade1()) {
                    case 1:
                        setRange((getRange() * 1.2));
                        break;
                    case 2:
                        setRange((getRange() * 1.4));
                        break;
                    case 3:
                        setRange((getRange() * 1.6));
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setProjectileDamage(getProjectileDamage()*1.2);
                        break;
                    case 2:
                        setProjectileDamage(getProjectileDamage()*1.4);
                        break;
                    case 3:
                        setProjectileDamage(getProjectileDamage()*1.7);
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