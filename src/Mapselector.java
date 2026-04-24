import greenfoot.*;

public class Mapselector extends World
{

    public Mapselector()
    {
        super(9, 9,60);
        setBackground("cell_grass.png");
        addObject(new MapPreview(1), 1,1);///Map Preview
        addObject(new MapPreview(2), 4,1);///Map Preview
        addObject(new MapPreview(3), 7,1);///Map Preview
        addObject(new MapPreview(4), 1,4);///Map Preview
        addObject(new MapPreview(5), 4,4);///Map Preview
        addObject(new MapPreview(6), 7,4);///Map Preview
        addObject(new MapPreview(7), 1,7);///Map Preview
        addObject(new MapPreview(8), 4,7);///Map Preview
        addObject(new MapPreview(9), 7,7);///Map Preview
    }





}