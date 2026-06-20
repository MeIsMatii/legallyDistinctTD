package map.menu;

import greenfoot.MouseInfo;
import util.HasSound;
import greenfoot.Greenfoot;
import greenfoot.World;
import ui.common.ImageDisplay;
import ui.hud.buttons.SettingsButton;
import ui.hud.gambling.SlotMachineButton;

public class MapSelector extends World implements HasSound {


    public MapSelector() {
        super(1920, 1080, 1);
        setPaintOrder( SettingsButton.class, SlotMachineButton.class, MapSelector.class, ImageDisplay.class);
        setBackground("cell_grass.png");
        addObject(new MapPreview(1), 350, 150);///Map Preview
        addObject(new MapPreview(2), 950, 150);///Map Preview
        addObject(new MapPreview(3), 1550, 150);///Map Preview
        addObject(new MapPreview(4), 350, 500);///Map Preview
        addObject(new MapPreview(5), 950, 500);///Map Preview
        addObject(new MapPreview(6), 1550, 500);///Map Preview
        addObject(new MapPreview(7), 350, 850);///Map Preview
        addObject(new MapPreview(8), 950, 850);///Map Preview
        addObject(new MapPreview(9), 1550, 850);///Map Preview
        addObject(new SettingsButton(), 50, 27);
        addObject(new SlotMachineButton(), 50, 1018);  //hört auf das zu entfernen
        addObject(new ImageDisplay("MenueBalken1.png", 960,40),1440,1060);
        addObject(new ImageDisplay("MenueBalken1.png", 960,40),480,1060);
        //addObject(new ImageDisplay("Credits.png", 300, 60), 10, 27);
        Greenfoot.start();
    }

    @Override
    public void act() {
        if (Greenfoot.isKeyDown("f1")){
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            System.out.println("X: " + mouseInfo.getX() + "  Y: " + mouseInfo.getY());
        }
    }
}