package ui.common;

import greenfoot.Greenfoot;
import greenfoot.World;
import ui.hud.buttons.Button;

public class BackButton extends Button {

    private final World lastWorld;

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
