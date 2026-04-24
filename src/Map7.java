import greenfoot.World;

public class Map7 extends World {
    public Map7()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new MapPreview(7), 3,3);
        addObject(new ComingSoon(), 6,6);

    }
}
