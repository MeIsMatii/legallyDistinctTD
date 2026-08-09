package ui.hud.buttons;

import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import maps.levels.GameMap;

public class DifficultySelector extends Button{
    private final GameMap map;

    public DifficultySelector(GameMap map, GameMap.Difficulty difficulty) {
        this.map = map;
        this.map.setDifficulty(difficulty);


        GreenfootImage img = new GreenfootImage(40,20);
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
        img.fillRect(0,0,img.getWidth(),img.getHeight());
        img.setColor(Color.BLACK);
        img.drawString(text, img.getWidth(),img.getHeight());
    }


    public void onClick() {
        Greenfoot.setWorld(map);
    }
}
