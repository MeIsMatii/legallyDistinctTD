import greenfoot.*;

import java.util.List;

public class Trap extends Item implements PickableItem{
    private final int damage;

    public Trap(int damage) {
        this.damage = damage;
    }

    public void addedToWorld(World thisWorld) {
        //sets itself to a random image from items in the world
        List<Item> items = thisWorld.getObjects(Item.class);
        int randNum = Greenfoot.getRandomNumber(items.size());
        setImage(items.get(randNum).getImage());
    }

    public PickableItem onPick(Character trigger) {
        // hit characters inside radius of 1
        List<Character> neighbours = getObjectsInRange(1, Character.class);
        for (Character character: neighbours) {
            character.hit(this.damage);
        }

        for (int i = 0; i < neighbours.size(); i++) {
            Character character = neighbours.get(i);
            character.hit(this.damage);
        }



        for (int y = -1; y < 2; y++) {
            if (getY() - 1 < 0 || getY() + 1 > getWorld().getHeight()) {
                continue;
            }
            for (int x = -1; x < 2; x++) {
                if (getX() - 1 < 0 || getX() + 1 > getWorld().getWidth()) {
                    continue;
                }
                getWorld().addObject(new Star(), getX() + x, getY() + y);
            }
        }
        PickableItem.super.onPick(trigger);
        return null;
    }

    @Override
    public void put() {
        return;
    }

}
