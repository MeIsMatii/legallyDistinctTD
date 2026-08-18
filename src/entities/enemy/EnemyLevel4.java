package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel4 extends Enemy {
    public EnemyLevel4() {
        super(4, 80);
        setImage("enemies/Minni-boss1.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel4";
    }
}