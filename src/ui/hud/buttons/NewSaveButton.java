package ui.hud.buttons;

import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.levels.*;
import map.menu.LoadingScreen;
import util.Clickable;
import util.saves.SaveManager;

public class NewSaveButton extends MainClass implements Clickable {
    Actor owner;
    int worldNr;
    private World loadingScreen = new LoadingScreen();


    public World getLoadingScreen() {
        return loadingScreen;
    }
    public void setLoadingScreen(World loadingScreen) {
        this.loadingScreen = loadingScreen;
    }
    public int getWorldNr() {
        return worldNr;
    }
    public void setWorldNr(int worldNr) {
        this.worldNr = worldNr;
    }


    public NewSaveButton(int Worldnr, Actor owner){
        GreenfootImage img = new GreenfootImage("NewSaveButton.png");
        this.owner = owner;
        img.scale(100,50);
        setImage(img);
        setWorldNr(Worldnr);
    }

    @Override
    public void act() {
        checkClick();
        if(getWorld().getObject(owner) {
            getWorld().removeObject(this);
        }
    }

    public void onClick() {
        LoadingScreen ls = (LoadingScreen) loadingScreen; //bc loadingScreen is a World
        switch (getWorldNr()) {///  erstellt die welt wenn man drauf drückt(welche kann im Konstruktor als Parameter angegeben werden)
            case 1:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map1());
                break;
            case 2:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map2());
                break;
            case 3:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map3());
                break;
            case 4:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map4());
                break;
            case 5:
                ls.setNextWorld(new Map5());
                Greenfoot.setWorld(getLoadingScreen());
                break;
            case 6:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map6());
                break;
            case 7:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map7());
                break;
            case 8:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map8());
                break;
            case 9:
                Greenfoot.setWorld(getLoadingScreen());
                ls.setNextWorld(new Map9());

                break;
        }
    }
}
