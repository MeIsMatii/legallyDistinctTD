package ui.hud.buttons;

import greenfoot.Greenfoot;
import greenfoot.World;
import maps.levels.GameMap;
import ui.hud.PopupScreen;

/**
 * @author Julian
 */
public class DifficultySelectorPopup extends PopupScreen {
    private final GameMap map;
    private final ClosePopupButton closeButton;

    public DifficultySelectorPopup(GameMap map) {
        this.map = map;
        closeButton = new ClosePopupButton(this);
    }


    public void addedToWorld(World w) {
        int buttonX = getX() + (getImage().getWidth() / 2) - 20;
        int buttonY = getY() - (getImage().getHeight() / 2) + 20;
        if (closeButton != null) {
            w.addObject(closeButton, buttonX, buttonY);
        }


        DifficultySelector difficultySelectorEasy = new DifficultySelector(map, GameMap.Difficulty.EASY);

        DifficultySelector difficultySelectorMedium = new DifficultySelector(map, GameMap.Difficulty.MEDIUM);

        DifficultySelector difficultySelectorHard = new DifficultySelector(map, GameMap.Difficulty.HARD);
        getWorld().addObject(difficultySelectorHard, getX() + getImage().getWidth() / 3, getY());
        getWorld().addObject(difficultySelectorMedium, getX(), getY());
        getWorld().addObject(difficultySelectorEasy, getX() - getImage().getWidth() / 3, getY());


    }

    public void act() {
        super.act();
        if ("escape".equals(Greenfoot.getKey())) {
            closeButton.onClick();
        }
    }


    public void onRemove() {
        for (PopupScreen p : getWorld().getObjects(PopupScreen.class)) {
            if (p != this) {
                getWorld().removeObject(p);
            }
        }
        if (closeButton != null) {
            getWorld().removeObject(closeButton);
        }
        for (DifficultySelector d : getWorld().getObjects(DifficultySelector.class)) {
            getWorld().removeObject(d);
        }
        getWorld().removeObject(this);
    }
}
