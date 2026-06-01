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
    public static void credits(String[] args){
        JFrame f = new JFrame();
        JLabel text = new JLabel("Credits");

        f.setSize(400, 400);
        f.setLayout(null);
        f.add(text);
        f.setVisible(true);                      //bruder keine ahnugn bin am verzweifeln

        text.setLocation(15,15);
    }
    public void credits() {
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
