import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author SAE
 * @version 3.0
 */
public class InventorySlot extends Actor
{
    private Actor item;

    // Corrected path
    //private static final GreenfootImage EMPTY_SLOT_IMG = new GreenfootImage("EmptySlot.png");
    //this was wrong
    private static final GreenfootImage EMPTY_SLOT_IMG = new GreenfootImage("./images/EmptySlot.png");
    public InventorySlot(){
        setImage(new GreenfootImage(InventorySlot.EMPTY_SLOT_IMG));
    }

    public InventorySlot(Actor item){
        this();
        setItem(item);
    }

    public void setItem(Actor item){
        this.item = item;
        setImage(new GreenfootImage(InventorySlot.EMPTY_SLOT_IMG));
        if(item!=null){
            getImage().drawImage(new GreenfootImage(item.getImage()), 5, 5);
        }
    }

    public Actor getItem(){
        return item;
    }
    /**
     * Act - do whatever the InventorySlot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
