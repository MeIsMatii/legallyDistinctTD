import greenfoot.Actor;
import greenfoot.World;

public interface PickableItem {
    abstract int getValue();
    abstract void setValue(int value);

    default PickableItem onPick(Character trigger) {
        Actor self = (Actor) this;
        World currWorld = self.getWorld();

        currWorld.removeObject(self);
        System.out.println("rmItem");
        return this;
    }
    default void put() {
        Actor self = (Actor) this;
        World currWorld = self.getWorld();
        currWorld.addObject(self, (self).getY(),(self).getX());
    }
    default void put(int x, int y, World world) {
        Actor self = (Actor) this;
        world.addObject(self, x,y);
    }
}
