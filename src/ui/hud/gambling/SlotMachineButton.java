package ui.hud.gambling;

import greenfoot.Greenfoot;
import ui.hud.buttons.Button;
/**
 * @author Jannis
 */

public class SlotMachineButton extends Button {

    public SlotMachineButton() {
        setImage("SlotMachine.png");
        getImage().scale(100, 100);
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new Gambling());
    }



}
