package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class TestBloon extends Enemy {
    public TestBloon(double speed, int lives) {
        super(speed, lives);
        setImage("enemies/arealEnemy1.png");
    }

    @Override
    public String getName() {
        return "test";
    }
}