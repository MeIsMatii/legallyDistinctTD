package ui.hud.buttons;

import greenfoot.Greenfoot;
import maps.menu.MapTitlescreen;
import util.saves.SaveManager;

/**
 * @author Colin
 */
public class GoToTutButton extends Button {
    public GoToTutButton() {
        setImage("GoToTut.png");
    }

    @Override
    public void onClick() {
        SaveManager.getInstance().setTutorialStatus(false);
        Greenfoot.setWorld(new MapTitlescreen());
    }

}
