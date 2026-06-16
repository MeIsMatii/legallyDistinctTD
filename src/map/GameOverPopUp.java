package map;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.settings.RetryButton;

public class GameOverPopUp extends Actor {

    private RetryButton retryButton;

    public GameOverPopUp() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.BLUE);
        img.fill();
        setImage(img);
    }

    public void addedToWorld(World w){
        retryButton = new RetryButton();

        w.addObject(retryButton,getX(),getY());

    }

}
