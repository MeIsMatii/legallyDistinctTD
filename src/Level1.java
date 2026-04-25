import greenfoot.World;

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Level1 extends World
{

    /**
     * Constructor for objects of class Level2.
     */
    public Level1()
    {
        super(8, 8, 60);
        setBackground("cell.jpg");
        setPaintOrder();

        prepare();


    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //HitboxCenter hitboxTester = new HitboxCenter(4,1);
        addObject(new Player(), 4,4);
        addObject(new idfk(), 7,4);
    }
}
