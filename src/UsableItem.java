import greenfoot.Actor;
import greenfoot.World;

public interface UsableItem {

    default void onUse(Character trigger){
        Actor item = (Actor) this;
        World currWorld = item.getWorld();
        currWorld.removeObject(item);
    }
}
