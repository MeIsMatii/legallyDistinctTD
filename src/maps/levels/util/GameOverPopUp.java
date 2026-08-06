package maps.levels.util;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.menu.MapSelector;
import ui.common.BackButton;
import ui.hud.PopupScreen;
import ui.hud.buttons.RetryButton;
import util.HasSound;

/// @Author Julian
public class GameOverPopUp extends PopupScreen implements HasSound {

    private RetryButton retryButton;
    private BackButton backButton;

    public GameOverPopUp() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.BLUE);
        img.fill();
        setImage(img);
    }

    public void addedToWorld(World w){
        retryButton = new RetryButton();
        backButton = new BackButton(new MapSelector());
        playSound("gameOverSound.mp3");
        w.addObject(retryButton,getX()+(getImage().getWidth()/4),getY());
        w.addObject(backButton,getX() - getImage().getWidth()/4,getY());

    }

    @Override
    public void onRemove() {
        getWorld().removeObject(retryButton);
        getWorld().removeObject(backButton);
        getWorld().removeObject(this);
    }
}
