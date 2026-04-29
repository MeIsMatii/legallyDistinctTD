import greenfoot.World;

public class Map3 extends World {
    public Map3()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new ComingSoon(), 6,6);

    }
}
