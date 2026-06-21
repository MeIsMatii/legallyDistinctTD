package map.menu;

import core.MainClass;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import ui.common.CustomImageDisplay;
import ui.common.ImageDisplay;
import util.Clickable;

import java.util.ArrayList;
import java.util.List;

public class MultiplayerPreview extends MainClass implements Clickable {
    CustomImageDisplay img = new CustomImageDisplay(700,500,"coming soon",new Color(139, 69, 19),Color.WHITE,new Font("Arial", true, false, 24));
    boolean clicked= false;
    public MultiplayerPreview(){
        GreenfootImage im = new GreenfootImage("Multiplayer.png");
        im.scale(500,300);
        setImage(im);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        if (!clicked){
            getWorld().addObject(img,960,540);
        }else {
            getWorld().removeObject(img);
        }
        clicked = !clicked;

    }
}
