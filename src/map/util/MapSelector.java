package map.util;

import entity.Player;
import greenfoot.*;
import setting.SettingsButton;
import util.HasSound;
import util.ImageDisplay;

public class MapSelector extends World implements HasSound
{


    public MapSelector()
    {
        super(29, 29,20);
        setPaintOrder(SettingsButton.class, MapSelector.class, ImageDisplay.class);
        setBackground("cell_grass.png");
        addObject(new MapPreview(1), 5,3);///Map Preview
        addObject(new MapPreview(2), 14,3);///Map Preview
        addObject(new MapPreview(3), 23,3);///Map Preview
        addObject(new MapPreview(4), 5,12);///Map Preview
        addObject(new MapPreview(5), 14,12);///Map Preview
        addObject(new MapPreview(6), 23,12);///Map Preview
        addObject(new MapPreview(7), 5,21);///Map Preview
        addObject(new MapPreview(8), 14,21);///Map Preview
        addObject(new MapPreview(9), 23,21);///Map Preview
        addObject(new SettingsButton(),27,27);
        for (int y =25; y<=29;y++){
            for(int i = 0; i < getWidth(); i++){
                addObject(new ImageDisplay("dirtsquare.png", 20,20),i, y);}
        }
        addObject(new ImageDisplay("Credits.png", 300, 60),10,27);
        addObject(new Player(),6,6);
        Greenfoot.start();
    }

}