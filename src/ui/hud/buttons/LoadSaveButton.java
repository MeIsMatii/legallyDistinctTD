package ui.hud.buttons;

import core.MainClass;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.levels.*;
import map.menu.LoadingScreen;
import util.Clickable;
import util.saves.GameSaveManager;
import util.saves.SaveManager;

public class LoadSaveButton extends Button{
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


    public LoadSaveButton(int Worldnr){
        GreenfootImage img = new GreenfootImage("LoadSaveButton.png");
        img.scale(100,50);
        setImage(img);
        setWorldNr(Worldnr);
    }


    public void onClick() {
        LoadingScreen ls = (LoadingScreen) loadingScreen; //bc loadingScreen is a World
        Map nextWorld = null;
        switch (getWorldNr()) {///  erstellt die welt wenn man drauf drückt(welche kann im Konstruktor als Parameter angegeben werden)
            case 1:
                nextWorld = new Map1();
                nextWorld.getGameSaveManager().setMapNr("map1");
                break;
            case 2:
                nextWorld = new Map2();
                nextWorld.getGameSaveManager().setMapNr("map2");
                break;
            case 3:
                nextWorld = new Map3();
                nextWorld.getGameSaveManager().setMapNr("map3");
                break;
            case 4:
                nextWorld = new Map4();
                nextWorld.getGameSaveManager().setMapNr("map4");
                break;
            case 5:
                nextWorld = new Map5();
                nextWorld.getGameSaveManager().setMapNr("map5");
                break;
            case 6:
                nextWorld = new Map6();
                nextWorld.getGameSaveManager().setMapNr("map6");
                break;
            case 7:
                nextWorld = new Map7();
                nextWorld.getGameSaveManager().setMapNr("map7");
                break;
            case 8:
                nextWorld = new Map8();
                nextWorld.getGameSaveManager().setMapNr("map8");
                break;
            case 9:
                nextWorld = new Map9();
                nextWorld.getGameSaveManager().setMapNr("map9");
                break;
        }
        Greenfoot.setWorld(getLoadingScreen());
        ls.setNextWorld(nextWorld);
        nextWorld.getGameSaveManager().reload();

        nextWorld.getGameSaveManager().loadGame(nextWorld);


        //todo Make it load game saves and not just enw worlds @Mathilo @Colin
    }
}
