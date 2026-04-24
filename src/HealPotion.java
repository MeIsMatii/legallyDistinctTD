public class HealPotion extends Potion{
    private final int healAmount;
    public HealPotion(int uses, int healAmount) {
        super(uses);
        this.healAmount = healAmount;
    }

    public void onUse(Character trigger) {
        trigger.heal(healAmount);

        setUses(getUses()-1);
        if(getUses()<=0) {
            getWorld().removeObject(this);
        }
    }
}
