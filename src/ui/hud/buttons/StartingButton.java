package ui.hud.buttons;

import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import map.menu.MapSelector;
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
            //TODO implement tutorial
        }
    }
}
