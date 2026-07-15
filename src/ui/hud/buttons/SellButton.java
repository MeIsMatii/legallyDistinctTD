package ui.hud.buttons;

import core.Player;
import entities.tower.Tower;
import greenfoot.GreenfootImage;
import map.levels.GameMap;
import util.HasSound;

public class SellButton extends Button implements HasSound {

    private final Tower tower;
    private final Player player;

    public SellButton(Tower tower, Player player) {
        this.tower = tower;
        this.player = player;
        GreenfootImage img = new GreenfootImage("sellButton.png");
        img.scale(100, 50);
        setImage(img);
    }

    @Override
    public void onClick() {
        getWorld().removeObject(tower);
        playSound("sellSound.mp3");
        player.setCoins(player.getCoins() + tower.getPrice() / 2);
        getWorldOfType(GameMap.class).setUpgradeMenuVisibility(false, tower);
    }
}
