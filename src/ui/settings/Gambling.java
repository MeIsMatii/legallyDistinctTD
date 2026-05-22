package ui.settings;

import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.common.HomeButton;

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
        addObject(new HomeButton(), 4, 4);
        addObject(new SlotMachineButtonGamble(), 15, 25);
        showText("Click! Click! Click!", 15, 30);


        slot1 = new SlotMachineSlot();
        slot2 = new SlotMachineSlot();
        slot3 = new SlotMachineSlot();

        GreenfootImage img1 = new GreenfootImage("chinaFlaggeGamble.png");
        GreenfootImage img2 = new GreenfootImage("lueckenfuellerGamble.jpg");
        GreenfootImage img3 = new GreenfootImage("lueckefueller2Gamble.jpg");

        img1.scale(100,100);
        img2.scale(100,100);
        img3.scale(100,100);

        slot1.setImage(img1);
        slot2.setImage(img2);
        slot3.setImage(img3);

        addObject(slot1,10,20);
        addObject(slot2,15,20);
        addObject(slot3,20,20);

    }
    //hier wird bald gegambelt
}
