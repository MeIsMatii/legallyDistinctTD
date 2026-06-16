package ui.common;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.World;

public class BackButton extends MainClass implements Clickable {

    private World lastWorld;

    public BackButton(World destination) {
        setImage("BackButton.png");
        getImage().scale(80, 80);
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
