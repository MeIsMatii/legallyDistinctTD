package maps.menu;

import core.MainClass;
import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.hud.PopupScreen;
import ui.hud.QuestionPopup;
import ui.hud.buttons.ClosePopupButton;
import ui.hud.buttons.LoadSaveButton;
import ui.hud.buttons.NewSaveButton;
import util.Clickable;
import util.multiplayer.NetworkManager;
import util.saves.GameSaveManager;

public class MapPreview extends MainClass implements Clickable {
    private boolean clicked = false;
    private int world = 0;
    private World loadingScreen;

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

    public World getLoadingScreen() {
        return loadingScreen;
    }

    public void setLoadingScreen(World loadingScreen) {
        this.loadingScreen = loadingScreen;
    }

    @Override
    public void act() {
        checkClick();
    }


    public void onClick() {
        if (!getWorld().getObjects(PopupScreen.class).isEmpty()) {
            System.out.println("Map popup could not be opened bc blocked by: " + getWorld().getObjects(PopupScreen.class));
            return;
        }
        setClicked(!isClicked());

        World world = getWorld();

        QuestionPopup questionPopup;
        NewSaveButton newSaveButton;

        if (NetworkManager.getInstance().isMultiplayer()) {
            newSaveButton = new NewSaveButton(getWorldNr(), true);
            ClosePopupButton closeButton = new ClosePopupButton(null); //to be set later

            newSaveButton.setImage("YesButton.png");
            closeButton.setImage("NoButton.png");

            questionPopup = new QuestionPopup("Start a new multiplayer session on \nmap " + getWorldNr() + "?", closeButton, newSaveButton); //TODO FIX (rm the loadsave
            closeButton.setOwner(questionPopup); //like now
        } else {
            GameSaveManager gameSaveManager = new GameSaveManager();
            newSaveButton = new NewSaveButton(getWorldNr(), false);

            if (gameSaveManager.saveFileExists("map" + getWorldNr() + ".save")) {
                questionPopup = new QuestionPopup("Do you want to continue your previous game?", new NewSaveButton(getWorldNr()), new LoadSaveButton(getWorldNr()));
            } else {
                ClosePopupButton closeButton = new ClosePopupButton(null); //to be set later

                newSaveButton.setImage("YesButton.png");
                closeButton.setImage("NoButton.png");
                questionPopup = new QuestionPopup("Would you like to start a new game on \nmap " + getWorldNr() + "?", closeButton, newSaveButton); //TODO fix
                closeButton.setOwner(questionPopup); //now is later

            }

        }

        world.addObject(questionPopup, 960, 540);

    }
}
