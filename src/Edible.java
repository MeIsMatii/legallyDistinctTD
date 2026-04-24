public class Edible extends Item implements PickableItem, UsableItem{
    private final int weight;

    public Edible(int weight){
        this.weight = weight;
        draw(weight);
        setValue(getValue()+(2*weight));
    }

    public int getWeight() {
        return weight;
    }

    public void onUse(Character trigger) {
        trigger.heal(weight);
    }
}
