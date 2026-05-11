package setting;

import greenfoot.Greenfoot;
import util.Clickable;
import util.MainClass;

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
