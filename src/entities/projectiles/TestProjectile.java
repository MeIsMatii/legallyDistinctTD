package entities.projectiles;

import entities.tower.Tower;

public class TestProjectile extends Projectile {
    public TestProjectile(Tower owner) {
        super(owner);
        setImage("heart.png");
    }


    void move() {
        move(getSpeed());
    }

}
