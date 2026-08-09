package maps.menu;

import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import maps.util.CustomWorld;
import ui.common.ImageDisplay;
import ui.hud.buttons.SettingsButton;
import ui.hud.gambling.SlotMachineButton;
import util.HasSound;
import util.multiplayer.MultiplayerPreview;
import util.saves.SaveManager;

/**
 * @author Colin
 * @author Mathilo
 */
public class MapSelector extends CustomWorld implements HasSound {
    public MapSelector() {
        super();
        setPaintOrder(ImageDisplay.class, SettingsButton.class, SlotMachineButton.class, MapSelector.class);
        setBackground("cell_grass.png");
        addObject(new MapPreview(1), 350, 175);///Map Preview
        addObject(new MapPreview(2), 950, 175);///Map Preview
        addObject(new MapPreview(3), 1550, 175);///Map Preview
        addObject(new MapPreview(4), 350, 525);///Map Preview
        addObject(new MapPreview(5), 950, 525);///Map Preview
        addObject(new MapPreview(6), 1550, 525);///Map Preview
        addObject(new MapPreview(7), 350, 875);///Map Preview
        addObject(new MapPreview(8), 950, 875);///Map Preview
        addObject(new MultiplayerPreview(), 1550, 875);///Map Preview
        addObject(new SettingsButton(), 50, 27);
        addObject(new SlotMachineButton(), 30, 1018);  //hört auf das zu entfernen       //50,1018 bei anderem bild
        addObject(new ImageDisplay("MenueBalken1.png", 960, 40), 1440, 1060);
        addObject(new ImageDisplay("MenueBalken1.png", 960, 40), 480, 1060);
        //addObject(new ImageDisplay("Credits.png", 300, 60), 10, 27);
        Greenfoot.start();
        lastPlayed();
    }

    @Override
    public void act() {
        if (Greenfoot.isKeyDown("f1")) {
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            System.out.println("X: " + mouseInfo.getX() + "  Y: " + mouseInfo.getY());
        }
    }

    public void lastPlayed() {
        SaveManager saveManager = SaveManager.getInstance();
        int lastPlayedInt = saveManager.getLastMap();

        int x;
        int y;
        int offsetX = 250;
        int offsetY = 125;
        if (lastPlayedInt >= 9 || lastPlayedInt == 0) {
            return;
        }
        switch (lastPlayedInt) {
            case 1:
                x = 350 + offsetX;
                y = 150 - offsetY;
                break;
            case 2:
                x = 950 + offsetX;
                y = 150 - offsetY;
                break;
            case 3:
                x = 1550 + offsetX;
                y = 150 - offsetY;
                break;
            case 4:
                x = 350 + offsetX;
                y = 500 - offsetY;
                break;
            case 5:
                x = 950 + offsetX;
                y = 500 - offsetY;
                break;
            case 6:
                x = 1550 + offsetX;
                y = 500 - offsetY;
                break;
            case 7:
                x = 350 + offsetX;
                y = 850 - offsetY;
                break;
            case 8:
                x = 950 + offsetX;
                y = 850 - offsetY;
                break;
            default:
                return; // no valid last map, don't place marker
        }

        addObject(new ImageDisplay("buttons/lastPlayed.png", 100, 100), x, y);
    }
}