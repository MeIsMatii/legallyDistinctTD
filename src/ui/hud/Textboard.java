package ui.hud;

import core.MainClass;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Textboard extends MainClass {
    public Textboard (int x,int y){
        GreenfootImage img = new GreenfootImage("Textboard.png");
        img.scale(x,y);
        setImage(img);
        //setText();
    }
    public void setText(String text){



    }
}

