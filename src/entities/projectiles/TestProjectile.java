package entities.projectiles;

public class TestProjectile extends Projectile{
    public TestProjectile(int speed, int damage, int piercing, int targetX, int targetY, int iframes) {
        super(speed, piercing, damage, targetX, targetY, iframes);
        setImage("heart.png");
    }


    void move() {
        move(getSpeed());
    }

}
