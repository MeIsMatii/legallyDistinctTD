package ui.common;

import ui.hud.buttons.Button;
import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.World;

public class BackButton extends Button {

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


}
