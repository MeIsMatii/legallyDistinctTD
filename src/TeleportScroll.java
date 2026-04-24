import greenfoot.World;

public class TeleportScroll extends Item implements PickableItem, UsableItem{
    private int teleportX;
    private int teleportY;


    public void addedToWorld(World world) {
        this.teleportX = getX();
        this.teleportY = getY();
    }
    public PickableItem onPick(Character trigger) {
        this.teleportX = getX();
        this.teleportY = getY();
        PickableItem.super.onPick(trigger);
        return this;
    }

    public void onUse(Character trigger) {
        trigger.setLocation(teleportX,teleportY);
        UsableItem.super.onUse(trigger);
    }
}
