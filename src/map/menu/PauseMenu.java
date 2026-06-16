package map.menu;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import javafx.scene.image.Image;
import ui.common.BackButton;
import ui.settings.PlayOnButton;
import ui.settings.RetryButton;
import ui.settings.SettingsButton;


public class PauseMenu extends Actor {

   private BackButton backButton;
   private SettingsButton settingsButton;
   private PlayOnButton playOnButton;
   private RetryButton retryButton;

    public PauseMenu() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.GRAY);
        img.fill();
        setImage(img);
    }




    public void addedToWorld(World w){
        backButton= new BackButton(new MapSelector());
        settingsButton = new SettingsButton();
        playOnButton = new PlayOnButton();
        retryButton = new RetryButton();

        w.addObject(backButton,getX()-getImage().getWidth()/3, getY());
        w.addObject(settingsButton,getX(), getY());
        w.addObject(playOnButton,getX()+500,getY());
        w.addObject(retryButton,getX(),getY()- getImage().getHeight()/3);
    }

public void onRemove(){
        World w = getWorld();
        w.removeObject(settingsButton);
        w.removeObject(backButton);
        w.removeObject(playOnButton);
        w.removeObject(retryButton);
        w.removeObject(this);
}

}

