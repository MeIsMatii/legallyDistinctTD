package map.menu;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.common.HomeButton;
import ui.settings.SettingsButton;


public class PauseMenu extends Actor {

   private HomeButton homeButton;
   private SettingsButton settingsButton;//TODO different Class

    public HomeButton getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(HomeButton homeButton) {
        this.homeButton = homeButton;
    }

    public SettingsButton getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(SettingsButton settingsButton) {
        this.settingsButton = settingsButton;
    }

    public PauseMenu() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.GRAY);
        img.fill();
        setImage(img);
    }

    public void addedToWorld(World w){
        setHomeButton(new HomeButton());
        setSettingsButton(new SettingsButton());

        getWorld().addObject(getHomeButton(),getX()-getImage().getWidth()*(1/3), getY());
        getWorld().addObject(getSettingsButton(),getX(), getY());

    }

public void onRemove(){
        World w = getWorld();
        w.removeObject(getSettingsButton());
        w.removeObject(getHomeButton());
        w.removeObject(this);
}

}

