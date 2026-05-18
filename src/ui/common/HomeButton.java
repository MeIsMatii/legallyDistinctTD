package ui.common;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.World;
import map.menu.MapSelector;

public class HomeButton extends MainClass implements Clickable {

    public HomeButton() {
        setImage("homeButton.png");
        getImage().scale(40, 40);

    }

    public void getBack(World destination) {
        Greenfoot.setWorld(destination);
    }

    @Override
    public void onClick() {
        getBack(new MapSelector());
    }

    @Override
    public void act() {
        checkClick();
    }

}
