package map.menu;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.common.BackButton;
import ui.settings.PlayOnButton;
import ui.settings.SettingsButton;


public class PauseMenu extends Actor {

   private BackButton homeButton;
   private SettingsButton settingsButton;
   private PlayOnButton playOnButton;

    public PauseMenu() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.GRAY);
        img.fill();
        setImage(img);
    }

    public BackButton getHomeButton() {
        return homeButton;
    }
    public void setHomeButton(BackButton homeButton) {
        this.homeButton = homeButton;
    }

    public SettingsButton getSettingsButton() {
        return settingsButton;
    }
    public void setSettingsButton(SettingsButton settingsButton) {
        this.settingsButton = settingsButton;
    }

    public PlayOnButton getPlayOnButton() {return playOnButton;}
    public void setPlayOnButton(PlayOnButton playOnButton) {this.playOnButton = playOnButton;}



    public void addedToWorld(World w){
        setHomeButton(new BackButton(new MapSelector()));
        setSettingsButton(new SettingsButton());
        setPlayOnButton(new PlayOnButton());

        w.addObject(getHomeButton(),getX()-getImage().getWidth()/3, getY());
        w.addObject(getSettingsButton(),getX(), getY());
        w.addObject(getPlayOnButton(),getX()+500,getY());
    }

public void onRemove(){
        World w = getWorld();
        w.removeObject(getSettingsButton());
        w.removeObject(getHomeButton());
        w.removeObject(getPlayOnButton());
        w.removeObject(this);
}

}

