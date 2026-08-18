package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel5 extends Enemy {
    public EnemyLevel5() {
        super(2, 100);
        setImage("enemies/leviathan.png"); //TODO FIX @elias/art guys
    }

    @Override
    public String getName() {
        return "EnemyLevel5";
    }
}