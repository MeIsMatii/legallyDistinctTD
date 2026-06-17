package map.levels.util;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.menu.MapSelector;
import ui.common.BackButton;
import ui.hud.buttons.RetryButton;

public class GameOverPopUp extends Actor {

    private RetryButton retryButton;
    private BackButton backButton;

    public GameOverPopUp() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.BLUE);
        img.fill();
        setImage(img);
    }

    public void addedToWorld(World w){
        retryButton = new RetryButton();
        backButton = new BackButton(new MapSelector());

        w.addObject(retryButton,getX()+(getImage().getWidth()/4),getY());
        w.addObject(backButton,getX() - getImage().getWidth()/4,getY());

    }

}
