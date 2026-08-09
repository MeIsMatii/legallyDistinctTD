package maps.levels.tutorial;

import greenfoot.GreenfootImage;
import maps.util.CustomWorld;
import ui.common.TutorialHud;
import util.saves.SaveManager;

/**
 * @author Sophia
 */
public class TutorialText extends CustomWorld {
    public TutorialText() {
        super();
        GreenfootImage img = new GreenfootImage("TutorialText.jpeg");
        img.scale(1920, 1080);
        setBackground(img);
        addObject(new TutorialHud(), 960, 1040);
        SaveManager.getInstance().setTutorialStatus(true);
    }
}
