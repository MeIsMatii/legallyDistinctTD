package entities.tower;

import entities.enemy.Enemy;

public class Helicopter extends Tower{
    public Helicopter() {
        super(0, true, 250, 50, 1, 10, 1, 45);
    }

    @Override
    public String upgrade1() {
        return "";
    }

    @Override
    public String upgrade2() {
        return "";
    }

    @Override
    public String upgrade3() {
        return "";
    }

    @Override
    void shoot(Enemy e) {

    }

    @Override
    public String getName() {
        return "Helicopter";
    }

    @Override
    public int getAnimationSpeed() {
        return 0;
    }
}
