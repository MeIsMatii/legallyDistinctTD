package ui.common;

import greenfoot.Greenfoot;
import maps.levels.tutorial.TutorialMap;
import ui.hud.buttons.Button;
import util.Clickable;

public class TutorialHud extends Button implements Clickable {
    public TutorialHud() {
        setImage("buttons/StartingButton.PNG");
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new TutorialMap());
    }
}
