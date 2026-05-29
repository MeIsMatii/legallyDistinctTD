package ui.hud;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import map.levels.Map;
import map.menu.MapSelector;
import util.SaveManager;

public class StartingButton extends MainClass implements Clickable {
    public StartingButton(){
        setImage("StartingButton.PNG");
    }

    @Override
    public void act() {
        checkClick();
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
