package map.levels;

import core.Player;
import ui.common.HomeButton;
import ui.common.ImageDisplay;

public class Map9 extends Map {
    public Map9() {
        setBackground("cell.jpg");
        addObject(new ImageDisplay("comingSoon.png", 120, 100), 6, 6);
        addObject(new Player(), 6, 6);
        addObject(new HomeButton(), 0, 0);

    }
}
