package ui.settings.gambling;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class SlotMachineButton extends MainClass implements Clickable {

    public SlotMachineButton() {
        setImage("Slot-MachineDefault.png");
        getImage().scale(40, 40);
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new Gambling());
    }

    public void act() {
        checkClick();
    }

}
