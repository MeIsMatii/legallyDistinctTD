//import com.sun.media.jfxmedia.events.PlayerStateEvent;
import greenfoot.*;

import java.util.List;

/**
 * @author SAE
 * @version 3.0
 */
public class Player extends Character {

    //Attribute
    //TODO: 1) Attribut vom Typ InventoryVisualizer deklarieren mit dem Namen vizualizer
    private final PickableItem[] inventory;
    private Actor[] actorInv;
    private PickableItem currentItem;
    private int gold;
    private World currentWorld;

    //Konstruktoren
    //default
    public Player() {
        super(100);
        this.inventory = new PickableItem[8];
        this.actorInv = new Actor[this.inventory.length];
        this.gold = 0;

    }

    public Player(int invSize, int gold, int life) {
        super(life);
        this.inventory = new PickableItem[invSize];
        this.actorInv = new Actor[this.inventory.length];
        this.gold = gold;
    }


    //Methoden
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addedToWorld(World thisWorld) {
        this.currentWorld = thisWorld;


        Actor[] actorInv = invUpdate();
        InventoryVisualizer visualizer = new InventoryVisualizer(actorInv, this);
        currentWorld.addObject(visualizer, 0, thisWorld.getHeight() - 1);
        //TODO: 2) Erstellen Sie neues Objekt vom Typ InventoryVisualizer und übergeben die Karotten als das zu visualisierende Inventar.
        //TODO: 3) Plazieren Sie das Objekt vom Type InventoryVisualizer in der Welt (0, Höhe der Welt -1) => unten links


    }

    public Actor[] invUpdate() {
        for (int i = 0; i < this.inventory.length; i++) {
            PickableItem item = this.inventory[i];
            Actor itemActor = (Actor) item;
            actorInv[i] = itemActor;
        }
        return actorInv;
    }

    /**
     * Wird einmal pro Zeiteinheit aufgerufen
     */
    public void act() {
        invUpdate();
        performMovement();
        selectSlot();
        moveItem();
        interactMerchant();
        if (Greenfoot.isKeyDown("F")) {
            System.out.println("pickItem");
            pickItem();
        }

        if (Greenfoot.isKeyDown("R")) {
            putItem();
            System.out.println("putItem");
        }

        if (Greenfoot.isKeyDown("U")) {
            useItem();
            System.out.println("useItem");
        }

        if (Greenfoot.isKeyDown("SPACE")) {
            hitCharacter(30);
            System.out.println("hitCharacter");
        }

        if (Greenfoot.isKeyDown("F1")) {
            debug();
        }
        this.currentItem = inventory[getSelectedSlot()];
    }

    public void hitCharacter(int damage) {
        List<Character> characters = getWorld().getObjectsAt(getX(), getY(), Character.class);

        if (characters.size() < 2) {
            return;
        }

        if (characters.get(0) != this) {
            characters.get(0).hit(damage);
        } else {
            characters.get(1).hit(damage);
        }
    }


    public void selectSlot() {
        String lastKey = Greenfoot.getKey();
        for (int i = 0; i < inventory.length + 1; i++) {
            if (lastKey != null && lastKey.equals(String.valueOf(i))) {
                if (i == 0) {
                    setSelectedSlot(9);
                    return;
                }
                setSelectedSlot(i - 1);
                return;
            }
        }
    }

    public void moveItem() {
        if (!Greenfoot.isKeyDown("CONTROL") || getSelectedSlot() > inventory.length || currentItem == null) {
            return;
        }
        int slot = 0;
        boolean check = false;
        for (int i = 0; i < inventory.length + 1; i++) {
            if (Greenfoot.isKeyDown(String.valueOf(i))) {
                if (i == 0) {
                    if (inventory.length < 10) {
                        break;
                    }
                    slot = 9;
                    check = true;
                    break;
                }
                check = true;
                slot = i - 1;
                break;
            }
        }

        if (!check) {
            return;
        }
        if (currentItem == null) {
            inventory[getSelectedSlot()] = inventory[slot];
            inventory[slot] = null;
            return;
        }
        PickableItem objToAdd = currentItem;
        inventory[getSelectedSlot()] = inventory[slot];
        inventory[slot] = objToAdd;

    }

    public void pickItem() {
        if (getSelectedSlot() > inventory.length || !isTouching(PickableItem.class)) {
            return;
        }
        List<PickableItem> objs = getWorld().getObjectsAt(getX(), getY(), PickableItem.class);
        if (!objs.isEmpty()) {
            PickableItem obj = objs.get(0);
            PickableItem pickedObj = obj.onPick(this);
            ;
            if (pickedObj == null) {
                return;
            }
            if (currentItem == null) {
                inventory[getSelectedSlot()] = pickedObj; //pickedObj has to be an item bc it is not null
                return;  //beendet die gesamte Methode
            }
            // slot not empty
            //putSlot();

            PickableItem objToAdd = (PickableItem) currentItem;
            inventory[getSelectedSlot()] = pickedObj;
            objToAdd.put(getX(), getY(), getWorld());
        }
    }

    public void putItem() {
        if (getSelectedSlot() > inventory.length) {
            return;
        }
        if (currentItem != null) {
            PickableItem objToAdd = (PickableItem) currentItem;
            inventory[getSelectedSlot()] = null;
            objToAdd.put(getX(), getY(), getWorld());
        }
    }

    public void useItem() {
        List<UsableItem> items = getWorld().getObjectsAt(getX(), getY(), UsableItem.class);
        if (items.isEmpty()) {
            return;
        }
        items.get(0).onUse(this);
    }


    /**
     * W - A - S - D movement
     */
    private void performMovement() {
        if (Greenfoot.isKeyDown("W")) {
            turn(Direction.NORTH);
            move(1);
        } else if (Greenfoot.isKeyDown("S")) {
            turn(Direction.SOUTH);
            move(1);
        } else if (Greenfoot.isKeyDown("A")) {
            turn(Direction.WEST);
            move(1);
        } else if (Greenfoot.isKeyDown("D")) {
            turn(Direction.EAST);
            move(1);
        }

    }

    public void interactMerchant() {
        if (!isTouching(Merchant.class)) {
            return;
        }
        draw(getGold());
        List<Merchant> merchants = currentWorld.getObjectsAt(getX(), getY(), Merchant.class);
        Merchant merchant = merchants.get(0);
        if (Greenfoot.isKeyDown("E")) {
            merchant.setSelectedSlot(merchant.getSelectedSlot() + 1);
        }
        if (Greenfoot.isKeyDown("Q")) {
            merchant.setSelectedSlot(merchant.getSelectedSlot() - 1);
        }
        if (Greenfoot.isKeyDown("SPACE")) {
            buyItem();
        }
        if (Greenfoot.isKeyDown("TAB")) {
            sellItem();
        }
    }

    public void buyItem() {
        if (!isTouching(Merchant.class)) {
            return;
        }

        List<Merchant> merchants = currentWorld.getObjectsAt(getX(), getY(), Merchant.class);
        Merchant merchant = merchants.get(0);
        PickableItem item = merchant.getCurrentItem();
        if (item == null || getGold() < item.getValue()) {
            //no item selected or not enough money
            System.out.println("cannot buy");
            return;
        }
        if (currentItem == null) {
            inventory[getSelectedSlot()] = item;
        } else {
            // slot not empty
            PickableItem objToAdd = currentItem;
            inventory[getSelectedSlot()] = item;
            getWorld().addObject((Actor) objToAdd, getX(), getY());
        }
        setGold(getGold() - item.getValue()); //remove the money
        merchant.buyItem(merchant.getSelectedSlot());  //one item per slot
    }

    public void sellItem() {
        if (!isTouching(Merchant.class) || currentItem == null) {
            return;
        }
        setGold(getGold() + currentItem.getValue());
        inventory[getSelectedSlot()] = null;
    }


    public void debug() {
        System.out.printf("Life: %d \n", getLife());
        System.out.printf("Gold: %d \n", getGold());
        System.out.printf("HeldItem: %s \n", currentItem);
    }
}
