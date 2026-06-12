package map.levels;

import core.Player;
import map.menu.MapSelector;
import ui.common.HomeButton;
import ui.common.ImageDisplay;

public class Map6 extends Map {
    public Map6() {
        setBackground("cell.jpg");
        addObject(new ImageDisplay("comingSoon.png", 120, 100), 6, 6);
        addObject(new Player(), 6, 6);
        addObject(new HomeButton(new MapSelector()), 0, 0);

    }
}
