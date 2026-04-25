import greenfoot.World;

public class MapSettings extends World {
    public MapSettings(){
        super(29, 29,20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(),4,4);
    }
}
