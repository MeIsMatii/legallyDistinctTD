package entities.projectiles;

import entities.tower.Tower;

public class FlameProjectile extends Projectile {
    public FlameProjectile(Tower owner) {
        super(owner);
        setImage("flame.png");
    }

    void move() {
        move(getSpeed());
    }

}

