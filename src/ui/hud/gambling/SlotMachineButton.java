package ui.hud.gambling;

import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class SlotMachineButton extends MainClass implements Clickable {

    public SlotMachineButton() {
        setImage("Slot-MachineDefault.png");
        getImage().scale(50, 50);
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new Gambling());
    }

    public void act() {
        checkClick();
    }

}
