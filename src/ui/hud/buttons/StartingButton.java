package ui.hud.buttons;

import greenfoot.Greenfoot;
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
            //TODO implement tutorial
        }
    }
}
