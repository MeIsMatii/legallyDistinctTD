package ui.settings.gambling;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.UserInfo;
import greenfoot.UserInfoVisitor;

public class SlotMachineButtonGamble extends MainClass implements Clickable {

    public SlotMachineButtonGamble() {
        setImage("Slot-MachineDefault.png");
        getImage().scale(200, 200);
    }

    @Override
    public void onClick() {
        Gambling world = (Gambling) getWorld();


        SlotMachineSlot[] slots = new SlotMachineSlot[]{
                world.getSlot1(),
                world.getSlot2(),
                world.getSlot3()
        };



        GreenfootImage[] images = new GreenfootImage[]{
                world.getSlot1().getImage(),
                world.getSlot2().getImage(),
                world.getSlot3().getImage()
        };

        int stop = Greenfoot.getRandomNumber(15);
        for (int i = 0; i < stop; i++) {


            for (int j = 0; j < 3; j++) {
                int random = Greenfoot.getRandomNumber(3);
                slots[j].setImage(images[random]);
                Greenfoot.delay(240);

            }

            Greenfoot.delay(3000000);

        }
        if (slots[0].getImage().equals(slots[1].getImage()) && slots[0].getImage().equals(slots[2].getImage())) {
            Greenfoot.delay(300000);
            Greenfoot.setWorld(new GamblingWonCredits());
        } else {
            getWorld().showText("You loose. Try again", 15, 20);
            Greenfoot.delay(3000000);
            getWorld().showText("",15,20);
        }

    }//slot machien bild weg dafür 3 bilder oder objekte nebeneinander jede viertel sekunde ändern
    //zufallszahl und wenn z.b. 5 ist dann anhalten

    public void act() {
        checkClick();
    }

}
