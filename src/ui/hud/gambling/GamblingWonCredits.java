package ui.hud.gambling;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.menu.MapSelector;
import ui.common.BackButton;

public class GamblingWonCredits extends World {

    private String[] credits = new String[]{
        "Bloons TD China",
        "Group Leader: Colin",
        "deputy Group Leader: Mathilo",
        "Programming: Jannis",
        "Programming/Music: Julian",
        "Art: Febo",
        "Art: Sophia",
        "Documentation: Jan",
        "(everyone had a programming part)"
    };

    private int creditsLocation = 0;
    private int counter = 0;

    private boolean creditsDone = false;


    public GamblingWonCredits() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        setPaintOrder(BackButton.class, TextBlock.class);
        addObject(new BackButton(new MapSelector()), 4, 4);
        credits();
    }


    public void credits() {

        GreenfootImage img = new GreenfootImage(2000, 2000);
        img.setColor(Color.BLACK);
        img.fill();
        setBackground(img);


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
