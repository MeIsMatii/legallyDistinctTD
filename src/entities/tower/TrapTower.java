package entities.tower;

import core.Clickable;
import entities.Entity;
import entities.enemy.Enemy;
import entities.projectiles.Mine;
import entities.projectiles.TestProjectile;
import entities.tower.util.RangeDisplay;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.Path;
import ui.hud.UpgradeMenu;

import java.util.List;

public class TrapTower extends Tower implements Clickable{
    private int mineRadius;

    public TrapTower() {
        super(200,false, 80, 99999999, 200, 10, 1, 90);
        mineRadius = 150;

        setImage("cell.jpg");
    }

    @Override
    public String upgrade1() {
        return "";
    }

    @Override
    public String upgrade2() {
        return "";
    }

    @Override
    public String upgrade3() {
        return "";
    }

    public void shoot(Enemy e) {
        getWorld().addObject(new Mine(getProjectileSpeed(), mineRadius, getProjectileDamage(), e.getX(), e.getY(), getProjectileIFrames()), getX(), getY());
    }
}
