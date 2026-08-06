package maps.levels.tutorial;

import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.common.TutorialHud;
import util.saves.SaveManager;

public class TutorialText extends World {
    public TutorialText(){
        super(1920, 1080,1);
        GreenfootImage img = new GreenfootImage("TutorialText.jpeg");
        img.scale(1920,1080);
        setBackground(img);
        addObject(new TutorialHud(),960, 1040);
        SaveManager.getInstance().setTutorialStatus(true);
    }
}
