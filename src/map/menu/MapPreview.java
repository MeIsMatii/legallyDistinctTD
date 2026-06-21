package map.menu;

import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.levels.*;
import util.saves.SaveManager;

public class MapPreview extends MainClass  implements Clickable{
    private boolean clicked = false;
    private int world = 0;

    public int getWorldNr() {
        return world;
    }

    public void setWorldNr(int world) {
        this.world = world;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    private World loadingScreen;


    public World getLoadingScreen() {
        return loadingScreen;
    }

    public void setLoadingScreen(World loadingScreen) {
        this.loadingScreen = loadingScreen;
    }


    public MapPreview(int worldNumber) {
        setWorldNr(worldNumber);
        setLoadingScreen(new LoadingScreen());
        if (getWorldNr() > 9 || getWorldNr() < 0) {
            System.out.println("Too high or low World number Fix map maker");
            return;
        }

        // 1. Determine the correct image path string first
        String imagePath = "Map" + getWorldNr() + "Preview.png";

        // 2. Load the image independently, scale it, and THEN apply it to the Actor
        GreenfootImage img = new GreenfootImage(imagePath);
        img.scale(500, 300);
        setImage(img); // This ensures the actor's hitbox matches the 500x300 size exactly
    }

    @Override
    public void act() {
        checkClick();
    }


    public void onClick() {
        SaveManager saveManager = SaveManager.getInstance();
        setClicked(!isClicked());
        saveManager.setLastMap(getWorldNr());
        switch (getWorldNr()) {///  erstellt die welt wenn man drauf drückt(welche kann im Konstruktor als Parameter angegeben werden)
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
        }

    }
}
