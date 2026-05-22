package ui.settings.gambling;

import greenfoot.World;
import ui.common.HomeButton;

public class GamblingWonCredits extends World {

    public GamblingWonCredits() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(), 4, 4);
        credits();
    }

    //hier wird bald gegambelt
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
