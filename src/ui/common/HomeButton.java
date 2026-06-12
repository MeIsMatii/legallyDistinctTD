package ui.common;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.World;
import map.menu.MapSelector;

public class HomeButton extends MainClass implements Clickable {

    private World lastWorld;

    public HomeButton(World destination) {
        setImage("homeButton.png");
        getImage().scale(40, 40);
        lastWorld = destination;

    }

    public void getBack(World destination) {
        Greenfoot.setWorld(destination);

    }

    @Override
    public void onClick() {
        getBack(lastWorld);
    }

    @Override
    public void act() {
        checkClick();
    }

}
