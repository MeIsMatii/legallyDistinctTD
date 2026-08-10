package ui.hud.gambling;

import greenfoot.GreenfootImage;
import maps.util.CustomWorld;
import ui.common.BackButton;
import ui.common.ImageDisplay;

/**
 * @author Jannis
 */
public class Gambling extends CustomWorld {

    private final ImageDisplay slot1;
    private final ImageDisplay slot2;
    private final ImageDisplay slot3;


    public Gambling() {
        super(29, 29, 20);


        setBackground("gambleBackground.png");
        addObject(new BackButton(), 4, 4);
        addObject(new SlotMachineButtonGamble(), 10, 16);
        showText("Click! Click! Click!", 14, 24);


        GreenfootImage img1 = new GreenfootImage("Gamble1.png");
        GreenfootImage img2 = new GreenfootImage("Gamble2.png");
        GreenfootImage img3 = new GreenfootImage("Gamble3.png");

        img1.scale(500, 500);
        img2.scale(500, 500);
        img3.scale(500, 500);

        slot1 = new ImageDisplay(img1);
        slot2 = new ImageDisplay(img2);
        slot3 = new ImageDisplay(img3);


        addObject(slot1, 25, 18);
        addObject(slot2, 22, 18);
        addObject(slot3, 19, 18);

    }

    public ImageDisplay getSlot1() {
        return slot1;
    }

    public ImageDisplay getSlot2() {
        return slot2;
    }

    public ImageDisplay getSlot3() {
        return slot3;
    }

}
