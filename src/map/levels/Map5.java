package map.levels;

import core.Player;
import map.menu.MapSelector;
import ui.common.BackButton;
import ui.common.ImageDisplay;

public class Map5 extends Map {
    public Map5() {
        setBackground("cell.jpg");
        addObject(new ImageDisplay("comingSoon.png", 120, 100), 6, 6);
        addObject(new Player(), 6, 6);
        addObject(new BackButton(new MapSelector()), 0, 0);

    }

    @Override
    public int getMapNumber() {
        return 5;
    }
}
