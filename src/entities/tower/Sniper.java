package entities.tower;

import entities.enemy.Enemy;

import java.util.List;

public class Sniper extends Tower {

    public Sniper() {
        super(200, true, 150, 100, 10, 0, 0, 0);

        //TODO UPGRADES
        upgradeDescription3 = new String[]{"Freeze damages enemy's", "Freeze damages enemy's even more", "The deadlyist freeze"};
        upgradeDescription2 = new String[]{"Ice slows enemy's more", "Ice freezes enemy's completely", "Enemy's are frozen longer"};
        upgradeDescription1 = new String[]{"More range", "Even more range", "Very long range"};

        upgrade1Prices = new int[]{150, 500, 2500};
        upgrade2Prices = new int[]{200, 450, 3000};
        upgrade3Prices = new int[]{100, 350, 1750};
    }



    @Override
    public void shoot(Enemy e) {
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
        if (canShoot() && !isPlacing()) {
            setShootingDelayCounter(0);
            List<Enemy> enemies = getWorld().getObjects(Enemy.class);
            if (!enemies.isEmpty()) {
                turnTowards(enemies.get(0).getX(), enemies.get(0).getY());
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
