import java.util.List;

public class Sword extends Item implements PickableItem{
    private final int damage;

    public Sword(int damage) {
        //super();
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public Item onUse(Character trigger) {
        List<Character> characters = getWorld().getObjectsAt(trigger.getX(),trigger.getY(),Character.class);
        if(characters.size() > 1) { //the player is there atleast
            for (Character character : characters) {
                if(character != trigger) {
                    character.hit(damage);
                }
            }
        }
        return this;
    }
}
