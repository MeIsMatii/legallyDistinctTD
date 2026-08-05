package ui.hud.buttons;

import greenfoot.Greenfoot;
import maps.levels.tutorial.TutorialText;
import maps.menu.MapSelector;
import util.saves.SaveManager;

public class StartingButton extends Button{
    public StartingButton(){
        setImage("StartingButton.PNG");
    }

    @Override
    public void onClick() {

        if (SaveManager.getInstance().getTutorialStatus()){
            Greenfoot.setWorld(new MapSelector());
        }else{
            Greenfoot.setWorld(new TutorialText());
        }
    }
}
