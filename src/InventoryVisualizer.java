import greenfoot.*;

import java.util.List;

public class InventoryVisualizer extends Actor {

    private final InventorySlot[] slots;
    private final Actor[] inventory;
    private final Character owner;
    private HotbarSelector currentSelector = null;
    private int lastSelectedSlot = -1;

    public InventoryVisualizer(Actor[] inventory, Character owner) {
        getImage().setTransparency(0);
        slots = new InventorySlot[inventory.length];
        this.inventory = inventory;
        this.owner = owner;
    }

  /*  public InventoryVisualizer(List<Actor> inventory) {
        this(inventory.toArray(new Actor[0]));
    }*/

    public void act(){
        update();
    }

    protected void addedToWorld(World world){
        for(int i=0; i < slots.length; i++){
            slots[i] = createItemSlot(i);
        }
        updateSlotSelector();
    }

    private InventorySlot createItemSlot(int i) {
        InventorySlot slot = new InventorySlot();
        getWorld().addObject(slot, i, getY());
        return slot;
    }


    /**
     * Updates all inventory Slots at the bottom of the screen with the content of the given inventory Array
     */
    private void update() {
        int length = Math.min(inventory.length, this.slots.length);
        for (int i = 0; i < length; i++) {
            if (inventory[i] != this.slots[i].getItem()) {
                slots[i].setItem(inventory[i]);
            }
        }
        updateSlotSelector();
    }
    public void updateSlotSelector() {
        if (owner.getSelectedSlot() == lastSelectedSlot) {
            return;
        }
        HotbarSelector hotbarSelector = new HotbarSelector();
        getWorld().addObject(hotbarSelector, owner.getSelectedSlot(), getY());
        if (currentSelector != null) {
            getWorld().removeObject(currentSelector);
        }
        lastSelectedSlot = owner.getSelectedSlot();
        currentSelector = hotbarSelector;
    }


    //i did this myselfd
    public void removeInventory() {
        for (InventorySlot slot : slots) { //same as "for i in list"
            getWorld().removeObject(slot);
        }
        if (currentSelector != null) {
            getWorld().removeObject(currentSelector);
        }
        currentSelector = null;
    }

}
