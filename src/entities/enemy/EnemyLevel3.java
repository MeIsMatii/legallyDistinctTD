package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel3 extends Enemy {
    public EnemyLevel3() {
        super(1.5, 80);
        setImage("enemies/sicel.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel3";
    }
}
