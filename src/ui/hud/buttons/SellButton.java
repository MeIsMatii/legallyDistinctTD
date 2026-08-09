package ui.hud.buttons;

import core.Player;
import entities.tower.Tower;
import greenfoot.GreenfootImage;
import maps.levels.GameMap;
import util.HasSound;
import util.multiplayer.NetworkManager;

public class SellButton extends Button implements HasSound {

    private final Tower tower;
    private final Player player;

    public SellButton(Tower tower, Player player) {
        this.tower = tower;
        this.player = player;
        GreenfootImage img = new GreenfootImage("buttons/sellButton.png");
        img.scale(100, 50);
        setImage(img);
    }

    @Override
    public void onClick() {
        getWorld().removeObject(tower);
        playSound("sellSound.mp3");
        player.setCoins(player.getCoins() + tower.getPrice() / 2);
        getWorldOfType(GameMap.class).setUpgradeMenuVisibility(false, tower);

        NetworkManager nm = NetworkManager.getInstance();

        if (nm.isMultiplayer()) {
            String msg = "REMOVE_ENTITY" + "," + tower.getUniqueId();
            nm.sendData(msg);
        }
    }
}
