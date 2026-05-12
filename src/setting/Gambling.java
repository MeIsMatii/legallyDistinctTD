package setting;

import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;
import util.HasSound;

public class Gambling extends World{

    public Gambling() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(), 4, 4);
        addObject(new SlotMachineButtonGamble(), 15, 15);
        showText("Click! Click! Click!", 15, 20);
    }
                                                            //hier wird bald gegambelt
}
