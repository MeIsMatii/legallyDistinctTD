package ui.hud.buttons;

import map.levels.GameMap;
import map.menu.PauseMenu;

import java.util.List;

public class PlayOnButton extends Button{

    public PlayOnButton() {
        setImage("PlayOnButton.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {

        getWorldOfType(GameMap.class).onContinue();

        List<PauseMenu> pauseMenus = getWorld().getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()){
            for (PauseMenu pauseMenu : pauseMenus) {
                pauseMenu.onRemove();
            }
        }

    }


}
