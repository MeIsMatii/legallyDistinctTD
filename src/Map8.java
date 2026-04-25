import greenfoot.World;

public class Map8 extends World {
    public Map8()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new MapPreview(8), 3,3);
        addObject(new ComingSoon(), 6,6);

    }
}
