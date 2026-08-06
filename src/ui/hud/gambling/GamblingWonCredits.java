package ui.hud.gambling;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.menu.MapSelector;
import ui.common.BackButton;

public class GamblingWonCredits extends World {

    private final String[] credits = new String[]{
        "A long, loong time ago",
        "in a galaxy, far, faaar away",
        "there were several idiots",
        "who spent way too much\ntime on a school project",
        "",
        "The projects name was:",
        "Legally distinct Tower Defense",
        "",
        "The idiots were:",
        "Colin",
        "Mathilo",
        "Jannis",
        "Julian",
        "Febo",
        "Sophia",
        "Jan",
    };

    private int creditsLocation = 0;
    private int counter = 0;

    private boolean creditsDone = false;


    public GamblingWonCredits() {
        super(1920, 1080, 1);
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

        if(counter < 90) {
            counter++;
            return;
        }
        if(creditsLocation > credits.length -1 && !creditsDone ) {
            showText("Danke fuer's spielen;)" ,getWidth()/2, getHeight()/2);
            creditsDone = true;
            return;
        }
        counter = 0;
        addObject(new TextBlock(credits[creditsLocation], -4), getWidth()/2, getHeight()-25);
        creditsLocation++;
    }
}
