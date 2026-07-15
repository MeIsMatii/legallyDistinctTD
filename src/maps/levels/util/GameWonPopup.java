package maps.levels.util;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.menu.MapSelector;
import ui.common.BackButton;
import ui.hud.PopupScreen;
import ui.hud.buttons.PlayOnButton;
import ui.hud.buttons.RetryButton;

public class GameWonPopup extends PopupScreen {
    private PlayOnButton playOnButton;
    private BackButton backButton;

    public GameWonPopup() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.BLUE);
        img.fill();
        setImage(img);
    }

    public void addedToWorld(World w){
        playOnButton = new PlayOnButton();
        backButton = new BackButton(new MapSelector());

        w.addObject(playOnButton,getX()+(getImage().getWidth()/4),getY());
        w.addObject(backButton,getX() - getImage().getWidth()/4,getY());
        w.showText("Freeplay", getX()+(getImage().getWidth()/4),getY() + 30);

    }

    @Override
    public void onRemove() {
        getWorld().removeObject(playOnButton);
        getWorld().showText("", getX()+(getImage().getWidth()/4),getY() + 30);
        getWorld().removeObject(backButton);
        getWorld().removeObject(this);
    }
}
