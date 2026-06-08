package ui.settings.gambling;

import greenfoot.World;
import ui.common.HomeButton;

import javax.swing.*;

public class GamblingWonCredits extends World {

    public GamblingWonCredits() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        setPaintOrder(HomeButton.class, TextBlock.class , Background.class);
        addObject(new HomeButton(), 4, 4);
        credits();
    }


    public void credits() {


        addObject(new TextBlock("Bloons TD China", 3), 10, 15);
        addObject(new Background(), 0,0);

        /*showText("You win", 15, 15);
        showText("Jannis", 15, 16);
        showText("Colin", 15, 17);
        showText("Mathilo", 15, 18);
        showText("Febo", 15, 19);
        showText("Sophia", 15, 20);
        showText("Jan", 15, 21);
        showText("Elias", 15, 22);*/



    }
}
