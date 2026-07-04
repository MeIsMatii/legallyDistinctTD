package ui.hud.buttons;

import map.levels.Map;
import util.Clickable;
import core.MainClass;
import core.Player;
import map.menu.PauseMenu;

import java.util.List;

public class PlayOnButton extends Button{

    public PlayOnButton() {
        setImage("PlayOnButton.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {

        getWorldOfType(Map.class).onContinue();

        List<PauseMenu> pauseMenus = getWorld().getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()){
            for (PauseMenu pauseMenu : pauseMenus) {
                pauseMenu.onRemove();
            }
        }

    }


}
