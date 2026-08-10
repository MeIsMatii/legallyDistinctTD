package ui.hud.buttons;

import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import maps.levels.GameMap;

/**
 * @author Julian
 * @author Mathilo
 */
public class RetryButton extends Button {


    public RetryButton() {
        GreenfootImage img = new GreenfootImage(80, 30);

        img.setColor(Color.BLACK);
        img.drawRect(0, 0, img.getWidth(), img.getHeight());
        img.setColor(Color.WHITE);
        img.fillRect(0, 0, img.getWidth(), img.getHeight());

        GreenfootImage text = new GreenfootImage("buttons/RetryButton.png");
        text.scale(60, 60);

        img.drawImage(text, 10, -10);
        setImage(img);
    }

    public void onClick() {
        try {
            GameMap gameMap = (GameMap) getWorld().getClass().getDeclaredConstructor().newInstance();
            Greenfoot.setWorld(gameMap);
            gameMap.getGameSaveManager().createSaveFile();
        } catch (Exception e) {
            System.out.println("error lmao");
        }
    }


}
