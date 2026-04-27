import greenfoot.World;

public class Map2 extends World {
    public Map2()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new ComingSoon(), 6,6);

    }
}
