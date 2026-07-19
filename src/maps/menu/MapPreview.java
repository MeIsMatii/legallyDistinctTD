package maps.menu;

import core.MainClass;
import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.hud.PopupScreen;
import ui.hud.QuestionPopup;
import ui.hud.buttons.LoadSaveButton;
import ui.hud.buttons.NewSaveButton;
import util.Clickable;
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
            System.out.println(getWorld().getObjects(PopupScreen.class));
            return;
        }
        setClicked(!isClicked());

        World world = getWorld();
        GameSaveManager gameSaveManager = new GameSaveManager();
        if (gameSaveManager.saveFileExists("map" + getWorldNr() + ".save")) {
            QuestionPopup questionPopup = new QuestionPopup("Do you want to continue your previous game?", new NewSaveButton(getWorldNr()), new LoadSaveButton(getWorldNr()));

            world.addObject(questionPopup, 960, 540);
        } else {

            QuestionPopup questionPopup = new QuestionPopup("Press \"no\" if you wanna start a new game\n and \"ESCAPE\" or the \"X\" if you dont", new NewSaveButton(getWorldNr()), new NewSaveButton(getWorldNr())); //TODO fix
            world.addObject(questionPopup, 960, 540);
        }


    }
}
