package ui.settings;

import core.Clickable;
import core.MainClass;
import core.Player;
import greenfoot.Greenfoot;
import map.menu.PauseMenu;

import java.util.List;

public class PlayOnButton extends MainClass implements Clickable {

    public PlayOnButton() {
        setImage("PlayOnButton.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {

        List<Player>players=getWorld().getObjects(Player.class);
        if (!players.isEmpty()){
            for (Player player : players){
                player.onContinue();
                System.out.println("cont");
            }
        }

        List<PauseMenu> pauseMenus = getWorld().getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()){
            for (PauseMenu pauseMenu : pauseMenus) {
                pauseMenu.onRemove();
            }
        }

    }

    public void act() {
        checkClick();
    }

}
