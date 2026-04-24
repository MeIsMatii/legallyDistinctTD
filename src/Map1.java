import greenfoot.*;

import java.util.List;
public class Map1 extends World
{

    public Map1()
    {
        super(9, 9,60);
        setBackground("cell.jpg");
        addObject(new MapPreview(1), 3,3);
        addObject(new ComingSoon(), 6,6);

    }





}
