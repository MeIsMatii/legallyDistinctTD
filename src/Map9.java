import greenfoot.World;

public class Map9 extends World {
    public Map9()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new MapPreview(9), 3,3);
        addObject(new ComingSoon(), 6,6);

    }
}
