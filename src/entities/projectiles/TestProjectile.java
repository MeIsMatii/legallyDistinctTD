package entities.projectiles;

public class TestProjectile extends Projectile{
    public TestProjectile(int speed, int piercing, int damage, int targetX, int targetY) {
        super(speed, piercing, damage, targetX, targetY);
        setImage("heart.png");
    }

}
