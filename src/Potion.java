import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;


public class Potion extends Item implements PickableItem, UsableItem {
    private int uses;

    public Potion(int uses) {
        this.uses = uses;
    }

    public PickableItem onPick(Character trigger) {
        int number = Greenfoot.getRandomNumber(100);
        PickableItem.super.onPick(trigger);
        if (number < 5) {
            return null; //break
        }
        return this; //no break
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

    public void onUse(Character trigger) {
        UsableItem.super.onUse(trigger);
    }
}

