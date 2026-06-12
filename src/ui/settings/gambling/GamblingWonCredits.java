package ui.settings.gambling;

import greenfoot.World;
import ui.common.HomeButton;

import javax.swing.*;

public class GamblingWonCredits extends World {

    private String[] credits = new String[]{
        "Bloons TD China",
        "Mathilo",
        "Colin",
        "Jannis",
        "Febo",
        "Jan",
        "Sophia"
    };

    private int creditsLocation = 0;
    private int counter = 0;

    private boolean creditsDone = false;


    public GamblingWonCredits() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        setPaintOrder(HomeButton.class, TextBlock.class , Background.class);
        addObject(new HomeButton(), 4, 4);
        credits();
    }


    public void credits() {

        //addObject(new TextBlock("Bloons TD China", -0.4), 10, 15);



        addObject(new Background(), 0,0);
    }

    public void act() {
        if (creditsLocation > credits.length-1 && creditsDone){
            return;
        }

        if(counter < 45) {
            counter++;
            return;
        }
        if(creditsLocation > credits.length -1 && !creditsDone ) {
            showText("Danke fuer's spielen;)" ,getWidth()/2, getHeight()/2);
            creditsDone = true;
            return;
        }
        counter = 0;
        addObject(new TextBlock(credits[creditsLocation], -0.3), getWidth()/2, 25);
        creditsLocation++;
    }
}
