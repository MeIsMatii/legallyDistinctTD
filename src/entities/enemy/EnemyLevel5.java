package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel5 extends Enemy {
    public EnemyLevel5() {
        super(2.4, 150);
        setImage("enemies/Minni-boss1.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel5";
    }
}