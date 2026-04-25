import greenfoot.World;

public class Map6 extends World {
    public Map6()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new MapPreview(6), 3,3);
        addObject(new ComingSoon(), 6,6);

    }
}
