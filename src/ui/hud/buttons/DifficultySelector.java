package ui.hud.buttons;

import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import maps.levels.GameMap;

/**
 * @author Mathilo
 */
public class DifficultySelector extends Button {
    private final GameMap map;
    private final GameMap.Difficulty difficulty;

    public DifficultySelector(GameMap map, GameMap.Difficulty difficulty) {
        this.map = map;
        this.difficulty = difficulty;


        GreenfootImage img = new GreenfootImage(80, 40);
        String text = "";

        switch (difficulty) {
            case EASY: {
                img.setColor(Color.GREEN);
                text = "EASY";
                break;
            }
            case MEDIUM: {
                img.setColor(Color.YELLOW);
                text = "MEDIUM";
                break;
            }
            case HARD: {
                img.setColor(Color.RED);
                text = "HARD";
                break;
            }
        }
        img.fillRect(0, 0, img.getWidth(), img.getHeight());
        img.setColor(Color.BLACK);
        img.drawString(text, img.getWidth() / 2 - 20, 20);
        setImage(img);
    }


    public void onClick() {
        this.map.setDifficulty(difficulty);

        Greenfoot.setWorld(map);
    }
}
