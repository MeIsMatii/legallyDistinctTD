package ui.hud.buttons;

import map.levels.Map;
import map.menu.PauseMenu;
import util.Clickable;

import java.util.List;

public class WaveResetButton extends Button implements Clickable {



    public WaveResetButton(){
        setImage("WaveResetButton.png");
        getImage().scale(60,60);

    }

   public void act(){
        checkClick();
   }



    public void onClick(){
        ((Map) getWorld()).resetWave();

        List<PauseMenu> pauseMenus = getWorld().getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()){
            for (PauseMenu pauseMenu : pauseMenus) {
                pauseMenu.onRemove();
            }
        }
    }
}
