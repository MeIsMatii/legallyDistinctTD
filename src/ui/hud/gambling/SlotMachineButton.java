package ui.hud.gambling;

import core.MainClass;
import greenfoot.Greenfoot;
import util.Clickable;
/**
 * @author Jannis
 */
public class SlotMachineButton extends MainClass implements Clickable {

    public SlotMachineButton() {
        //setImage("Slot-MachineDefault.png");
        setImage("SlotMachine.png");
        //getImage().scale(50, 50);
        getImage().scale(100, 100);
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new Gambling());
    }

    public void act() {
        checkClick();
    }

}
