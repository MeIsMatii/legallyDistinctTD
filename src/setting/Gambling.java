package setting;

import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;
import util.HasSound;

public class Gambling extends World{

    public SlotMachineSlot1 slot1;
    public SlotMachineSlot2 slot2;
    public SlotMachineSlot3 slot3;

    public Gambling() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(), 4, 4);
        addObject(new SlotMachineButtonGamble(), 15, 25);
        showText("Click! Click! Click!", 15, 30);

        slot3 = new SlotMachineSlot3();
        addObject(slot3, 25, 15);

        slot2 = new SlotMachineSlot2();
        addObject(slot2, 15, 15);

        slot1 = new SlotMachineSlot1();
        addObject(slot1, 5, 15);
    }
                                                            //hier wird bald gegambelt
}
