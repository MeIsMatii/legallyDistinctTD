package ui.hud.buttons;

import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class RetryButton extends Button{


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


}
