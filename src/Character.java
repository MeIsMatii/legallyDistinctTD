import java.util.List;

public class Character extends MovingActor{
    private int selectedSlot = 0;
    private int life;

    public Character(int life) {
        this.life = life;
    }


    public int getLife() {
        return life;
    }
    public void setLife(int newLife) {
        life = newLife;
        draw(life);
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }
    public void setSelectedSlot(int selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public void hit(int damage) {
        getWorld().addObject(new Star(), getX(), getY()); //Bei einem Treffer wird kurz ein Stern eingeblendet
        setLife(getLife()-damage);
        if(getLife()<=0) {
            getWorld().removeObject(this);
        }
        draw(getLife());
    }

    public void heal(int life) {
        hit(-life);
        List<Star> stars = getWorld().getObjectsAt(getX(),getY(),Star.class);
        getWorld().removeObject(stars.get(0)); //remove the hit marker
    }

    public void die() {
        getWorld().removeObject(this);
    }

    public void act() {
        if(life<=0) {
            die();
        }
    }

}
