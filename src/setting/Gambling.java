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
    }
                                                            //hier wird bald gegambelt
}
