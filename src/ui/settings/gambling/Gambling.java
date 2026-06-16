package ui.settings.gambling;

import greenfoot.GreenfootImage;
import greenfoot.World;
import map.menu.MapSelector;
import ui.common.BackButton;

public class Gambling extends World {

    private SlotMachineSlot slot1;
    private SlotMachineSlot slot2;
    private SlotMachineSlot slot3;
    private int wins = 0;



    public SlotMachineSlot getSlot1() {
        return slot1;
    }

    public SlotMachineSlot getSlot2() {
        return slot2;
    }

    public SlotMachineSlot getSlot3() {
        return slot3;
    }

    public int getWins() {return wins;}



    public void setWins(int wins) {this.wins = wins;}



    public Gambling() {
        super(29, 29, 20);



        setBackground("dirtsquare.png");
        addObject(new BackButton(new MapSelector()), 4, 4);
        addObject(new SlotMachineButtonGamble(this), 15, 25);
        showText("Click! Click! Click!", 15, 30);
        showText("Wins: " + getWins(), 25,0);


        slot1 = new SlotMachineSlot();
        slot2 = new SlotMachineSlot();
        slot3 = new SlotMachineSlot();

        GreenfootImage img1 = new GreenfootImage("GambleOne.png");
        GreenfootImage img2 = new GreenfootImage("GambleTwo.png");
        GreenfootImage img3 = new GreenfootImage("GambleThree.png");

        img1.scale(120,120);
        img2.scale(120,120);
        img3.scale(120,120);

        slot1.setImage(img1);
        slot2.setImage(img2);
        slot3.setImage(img3);

        addObject(slot1,8,13);
        addObject(slot2,15,13);
        addObject(slot3,22,13);

    }
    //hier wird bald gegambelt
}
