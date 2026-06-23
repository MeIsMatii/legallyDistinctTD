package entities.projectiles;

import entities.tower.Tower;

public class FlameProjectile extends Projectile {
    public FlameProjectile(Tower owner) {
        super(owner);
        setImage("TempFire.jpg");
    }


    void move() {
        move(getSpeed());
    }

}

