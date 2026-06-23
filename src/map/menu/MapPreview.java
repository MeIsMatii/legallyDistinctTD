package map.menu;

import ui.common.BackButton;
import ui.hud.NewGamePopup;
import ui.hud.PopupScreen;
import ui.hud.buttons.CloseButton;
import ui.hud.buttons.LoadSaveButton;
import ui.hud.buttons.NewSaveButton;
import util.Clickable;
import core.MainClass;
import greenfoot.GreenfootImage;
import greenfoot.World;
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
        if (getWorldNr() > 8 || getWorldNr() < 0) {
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
        if(!getWorld().getObjects(PopupScreen.class).isEmpty()) {
            System.out.println(getWorld().getObjects(PopupScreen.class));
            return;
        }
        SaveManager saveManager = SaveManager.getInstance();
        setClicked(!isClicked());

        World world = getWorld();
        if(saveManager.getLastMap() == getWorldNr()) {
            System.out.println(1);
            NewGamePopup newGamePopup = new NewGamePopup("Do you want to continue your previous game?", getWorldNr(), new NewSaveButton(getWorldNr()), new LoadSaveButton(getWorldNr()));

            world.addObject(newGamePopup, 960, 540);
        } else {
            System.out.println(2);

            NewGamePopup newGamePopup = new NewGamePopup("!(Do you want to start a new game?)", getWorldNr(), new CloseButton(), new NewSaveButton(getWorldNr())); //TODO fix
            world.addObject(newGamePopup, 960, 540);
        }
        saveManager.setLastMap(getWorldNr());

    }
}
