package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel1 extends Enemy {
    public EnemyLevel1() {
        super(1, 5);
        setImage("enemies/arealEnemy1.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel1";
    }
}
