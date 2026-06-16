package ui.settings.gambling;

import greenfoot.GreenfootImage;
import greenfoot.World;
import map.menu.MapSelector;
import ui.common.BackButton;

public class Gambling extends World {

    private SlotMachineSlot slot1;
    private SlotMachineSlot slot2;
    private SlotMachineSlot slot3;



    public SlotMachineSlot getSlot1() {
        return slot1;
    }

    public SlotMachineSlot getSlot2() {
        return slot2;
    }

    public SlotMachineSlot getSlot3() {
        return slot3;
    }



    public Gambling() {
        super(29, 29, 20);



        setBackground("dirtsquare.png");
        addObject(new BackButton(new MapSelector()), 4, 4);
        addObject(new SlotMachineButtonGamble(this), 10, 16);
        showText("Click! Click! Click!", 14, 24);



        slot1 = new SlotMachineSlot();
        slot2 = new SlotMachineSlot();
        slot3 = new SlotMachineSlot();

        GreenfootImage img1 = new GreenfootImage("Gamble1.png");
        GreenfootImage img2 = new GreenfootImage("Gamble2.png");
        GreenfootImage img3 = new GreenfootImage("Gamble3.png");

        img1.scale(500,500);
        img2.scale(500,500);
        img3.scale(500,500);

        slot1.setImage(img1);
        slot2.setImage(img2);
        slot3.setImage(img3);

        addObject(slot1,25,18);
        addObject(slot2,22,18);
        addObject(slot3,19,18);

    }

}
