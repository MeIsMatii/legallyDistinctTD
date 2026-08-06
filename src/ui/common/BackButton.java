package ui.common;

import greenfoot.Greenfoot;
import greenfoot.World;
import maps.menu.MapSelector;
import ui.hud.buttons.Button;

public class BackButton extends Button {

    public BackButton() {
        setImage("buttons/BackButton.png");
        getImage().scale(80, 80);

    }

    public void getBack(World destination) {
        Greenfoot.setWorld(destination);

    }

    @Override
    public void onClick() {
        getBack(new MapSelector());
    }


}
