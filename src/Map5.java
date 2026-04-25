import greenfoot.World;

public class Map5 extends World {
    public Map5()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new MapPreview(5), 3,3);
        addObject(new ComingSoon(), 6,6);

    }
}
