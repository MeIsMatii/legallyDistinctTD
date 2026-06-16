package entities.tower;

import core.Clickable;
import entities.Entity;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.projectiles.Mine;
import greenfoot.World;
import map.levels.util.Path;

import java.util.List;

public class TrapTower extends Tower implements Clickable {
    private int mineRadius;
    private int[] upgrades1 = new int[]{150, 500, 2500, 7500, 17000};
    private int[] upgrades2 = new int[]{200, 450, 3000, 10000, 25000};
    private int[] upgrades3 = new int[]{100, 350, 1750, 6000, 9500};

    public TrapTower() {
        super(125, true, 80, 99999999, 200, 10, 1, 90);
        mineRadius = 150;

        setImage("TestTower2_2.png");
    }

    public int[] getUpgrades1() {
        return upgrades1;
    }

    public int[] getUpgrades2() {
        return upgrades2;
    }

    public int[] getUpgrades3() {
        return upgrades3;
    }

    @Override
    public String upgrade1() {
        setUpgrade1(getUpgrade1() + 1);
        return "";
    }

    @Override
    public String upgrade2() {
        setUpgrade2(getUpgrade2() + 1);
        return "";
    }

    @Override
    public String upgrade3() {
        setUpgrade3(getUpgrade3() + 1);
        return "";
    }

    public void shoot(Enemy e) {
    }

    public void act() {
        if (isPaused()) return;
        super.act();
    }

    public void onHit(Entity hitter) {
        //TODO explode when touching enemy (mine projectile is not necessary) @sinnaJ @Jannis
    }


    public void checkPlacement() {
        List<Hitbox> hitboxes = getIntersectingObjects(Hitbox.class);
        setCanPlace(false);
        for (Hitbox hitbox : hitboxes) {
            if (hitbox.getOWNER() instanceof Path) {
                setCanPlace(true);
                break;
            }
        }

        if (getCanPlace()) {
            getRANGEDISPLAY().setRangeVisibility(true, getColorGrey());
        } else {
            getRANGEDISPLAY().setRangeVisibility(true, getColorRed());
        }
    }


}
