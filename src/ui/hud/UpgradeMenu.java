package ui.hud;

import core.Player;
import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.World;
import ui.hud.buttons.SellButton;

import java.util.List;

public class UpgradeMenu extends Actor {
    private final Tower tower;
    private final UpgradePath path1;
    private final UpgradePath path2;
    private final UpgradePath path3;
    private SellButton sellButton;
    private Player player;

    public UpgradeMenu(Tower tower) {
        setImage("upgradeMenu.png");
        getImage().scale(1620, 216);
        this.tower = tower;
        path1 = new UpgradePath(this.tower, 1);
        path2 = new UpgradePath(this.tower, 2);
        path3 = new UpgradePath(this.tower, 3);
    }

    @Override
    protected void addedToWorld(World world) {
        List<Player> pl = world.getObjects(Player.class);
        if (!pl.isEmpty()) {
            player = pl.get(0);
        }

        sellButton = new SellButton(tower, player);

        world.addObject(path1, getX() - 500, getY());
        world.addObject(path2, getX(), getY());
        world.addObject(path3, getX() + 500, getY());
        world.addObject(sellButton, getX() + 700, getY());
    }

    public Tower getTower() {
        return this.tower;
    }

    public void delete() {
        World world = getWorld();
        if (world != null) {
            path1.onRemove();
            path2.onRemove();
            path3.onRemove();
            if (sellButton != null) {
                world.removeObject(sellButton); // Don't forget to remove the sell button too!
            }
            world.removeObject(this);
        }
    }
}