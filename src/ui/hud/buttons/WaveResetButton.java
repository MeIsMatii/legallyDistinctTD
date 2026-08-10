package ui.hud.buttons;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import maps.levels.GameMap;
import maps.menu.PauseMenu;
import util.Clickable;

import java.util.List;

/**
 * @author Julian
 * @author Mathilo
 */
public class WaveResetButton extends Button implements Clickable {


    public WaveResetButton() {
        GreenfootImage img = new GreenfootImage(80, 40);

        img.setColor(Color.BLACK);
        img.drawRect(0, 0, img.getWidth(), img.getHeight());
        img.setColor(Color.WHITE);
        img.fillRect(0, 0, img.getWidth(), img.getHeight());

        GreenfootImage text = new GreenfootImage("buttons/WaveResetButton.png");
        text.scale(60, 60);

        img.drawImage(text, 10, -5);
        setImage(img);


    }


    public void onClick() {
        ((GameMap) getWorld()).resetWave();
        ((GameMap) getWorld()).onContinue();


        List<PauseMenu> pauseMenus = getWorld().getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()) {
            for (PauseMenu pauseMenu : pauseMenus) {
                pauseMenu.onRemove();
            }
        }
    }
}
