package ui.settings;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import map.levels.Map;
import map.levels.Map1;
import map.levels.Map2;
import map.menu.MapPreview;
import map.menu.MapSelector;

public class RetryButton extends MainClass implements Clickable {


    public RetryButton() {
        setImage("RetryButton.png");
        getImage().scale(60, 60);
    }

    public void onClick(){
        try {
            Greenfoot.setWorld(getWorld().getClass().newInstance());
        } catch (Exception e) {
            System.out.println("error lmao");
        }
    }

    public void act(){
        checkClick();
    }

}
