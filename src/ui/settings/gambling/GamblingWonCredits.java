package ui.settings.gambling;

import greenfoot.World;
import ui.common.HomeButton;

import javax.swing.*;

public class GamblingWonCredits extends World {

    public GamblingWonCredits() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(), 4, 4);
        credits();
    }


    public void credits() {
        /*
        JFrame f = new JFrame();
        JLabel text = new JLabel("Credits");

        f.setSize(400, 400);
        f.add(text);
        f.setVisible(true);

        text.setLocation(15,15);

        for (int i = 3; i > 0; i--) {
            int v = text.getVerticalAlignment();
            text.setVerticalTextPosition(v - 1);   // ich lösche die welt jetzt udn zeichen halt so auf schwarzes bild die credits mach den in actor und lasse den im act  zyklus über die welt dingsen
        }
        /*
        for (int i = 3; i > 0; i--) {
            int v = text.getVerticalTextPosition();
            text.setVerticalTextPosition(v - 1);
        }
        */


        showText("You win", 15, 15);
        showText("Jannis", 15, 16);
        showText("Colin", 15, 17);
        showText("Mathilo", 15, 18);
        showText("Febo", 15, 19);
        showText("Sophia", 15, 20);
        showText("Jan", 15, 21);
        showText("Elias", 15, 22);



    }
}
