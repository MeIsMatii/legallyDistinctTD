package ui.settings;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class SlotMachineButtonGamble extends MainClass implements Clickable {

    public SlotMachineButtonGamble() {
        setImage("Slot-MachineDefault.png");
        getImage().scale(200, 200);
    }

    @Override
    public void onClick() {
        Gambling world = (Gambling) getWorld();
        String bild1 = "chinaFlaggeGamble.png";
        String bild2 = "lueckenfuellerGamble.jpg";
        String bild3 = "lueckefueller2Gamble.jpg";
        int stopp = Greenfoot.getRandomNumber(15);
        for (int i = 0; i < stopp; i++) {
            int random1 = Greenfoot.getRandomNumber(3);
            int random2 = Greenfoot.getRandomNumber(3);
            int random3 = Greenfoot.getRandomNumber(3);

            if (random1 == 0) {
                bild1 = "chinaFlaggeGamble.png";
            } else if (random1 == 1) {
                bild1 = "lueckenfuellerGamble.jpg";
            } else {
                bild1 = "lueckefueller2Gamble.jpg";
            }

            if (random2 == 0) {
                bild2 = "chinaFlaggeGamble.png";
            } else if (random2 == 1) {
                bild2 = "lueckenfuellerGamble.jpg";
            } else {
                bild2 = "lueckefueller2Gamble.jpg";
            }

            if (random3 == 0) {
                bild3 = "chinaFlaggeGamble.png";
            } else if (random3 == 1) {
                bild3 = "lueckenfuellerGamble.jpg";
            } else {
                bild3 = "lueckefueller2Gamble.jpg";
            }
            world.slot1.setImage(bild1);
            world.slot2.setImage(bild2);
            world.slot3.setImage(bild3);


            Greenfoot.delay(3);
        }
        if (bild1.equals(bild2) && bild2.equals(bild3)) {
            Greenfoot.delay(5);
            Greenfoot.setWorld(new GamblingWonCredits());
        } else {
            getWorld().showText("You loose. Try again", 15, 20);
        }

    }//slot machien bild weg dafür 3 bilder oder objekte nebeneinander jede viertel sekunde ändern
    //zufallszahl und wenn z.b. 5 ist dann anhalten

    public void act() {
        checkClick();
    }

}
