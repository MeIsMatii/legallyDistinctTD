package entities;

import core.Clickable;
import core.HasSound;
import core.MainClass;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class ExampleActor extends MainClass implements Clickable, HasSound {


    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!
    private final GreenfootSound backgroundMusic;
    private boolean clicked = false;

    public ExampleActor() {
        backgroundMusic = playSoundAndKeep("TEMPTropischSound.mp3");
    }

    @Override
    public void act() {
        super.act();
        if(isPaused()) {
            return;
        }
        checkClick();
        updateAppearance();
        if (Greenfoot.isKeyDown("F2")) {
            backgroundMusic.playLoop();
        }
        if (Greenfoot.isKeyDown("F1")) {
            backgroundMusic.stop();
        }
    }

    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!
    @Override
    public void onClick() {
        clicked = !clicked;
        updateAppearance();
        System.out.println("Actor was clicked! Toggled to: " + clicked);
    }

    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!
    private void updateAppearance() {
        GreenfootImage img = new GreenfootImage(60, 60);
        img.setColor(clicked ? Color.GREEN : Color.RED);
        img.fillOval(0, 0, 60, 60);
        setImage(img);
    }

    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!

}
