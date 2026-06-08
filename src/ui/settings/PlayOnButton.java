package ui.settings;

import core.Clickable;
import core.MainClass;
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
